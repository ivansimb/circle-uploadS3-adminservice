package sg.ihh.ms.as.app;

import sg.ihh.ms.as.app.configuration.EncryptConfig;

public class Console {

    public static void main(String[] args) {
        String[] strArr = {"192.168.0.207", "3306", "IHHDB", "ihh_user", "P@ssw0rd"};

        for (String str : strArr) {
            EncryptConfig config = new EncryptConfig();
            String encrypted = config.stringEncryptor().encrypt(str);
            System.out.println("PlainText : " + str);
            System.out.println("Encrypted : ENC(" + encrypted + ")");
        }

    }
}
