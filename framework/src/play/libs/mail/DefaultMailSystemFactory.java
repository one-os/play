package play.libs.mail;

import play.Play;
import play.libs.Mail;

public class DefaultMailSystemFactory extends AbstractMailSystemFactory {

    @Override
    public MailSystem currentMailSystem() {
        return mailSystem();
    }

    private static MailSystem mailSystem() {
        MailSystem mailSystem;
        if (useLegaycyMockMailSystem()) {
            mailSystem = new Mail.LegacyMockMailSystem();
        } else {
            mailSystem = new ProductionMailSystem();
        }
        return mailSystem;
    }

    private static boolean useLegaycyMockMailSystem() {
        return Play.configuration.getProperty("mail.smtp", "").equals("mock") && Play.mode == Play.Mode.DEV;
    }

}
