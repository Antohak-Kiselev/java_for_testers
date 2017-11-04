package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTest extends  TestBase{



  int userId = loadUsersFromDb().iterator().next().getId();
  private String password = "newpassword";


  @BeforeMethod
  public void startMailServer() {
    app.mail ().start ();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {

    app.change().loginAsAdmin();
    app.change().resetPassword(userId);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    UserData userData = app.db().userById(userId);
    String confirmationLink = findConfirmationLink(mailMessages, userData.getEmail());
    app.change().newPassword(confirmationLink, password);
    assertTrue(app.newSession().login(userData.getUsername(), password));

  }






  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public Users loadUsersFromDb() {

    Connection conn = null;

    try {
      conn = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&password=" );
      Statement st = conn.createStatement ();
      ResultSet rs = st.executeQuery ( "SELECT id, username FROM mantis_user_table" );
      Users users = new Users ();
      while (rs.next ()) {
        if (!(rs.getString ( "username" ).equals ( "administrator" ))) {
          users.add ( new UserData().withId ( rs.getInt ( "id" ) ).withName ( rs.getString ( "username" ) ) );
        }
      }
      rs.close ();
      st.close ();
      conn.close ();
      return users;

    } catch (SQLException ex) {
      System.out.println ( "SQLException: " + ex.getMessage () );
      System.out.println ( "SQLState: " + ex.getSQLState () );
      System.out.println ( "VendorError: " + ex.getErrorCode () );
    }
    return null;
  }


  @AfterMethod
  public void stopMailServer() {
    app.mail().stop();
  }

}