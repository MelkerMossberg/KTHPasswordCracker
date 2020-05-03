import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PasswordCrack {
    private static ArrayList unsolvedPasswords = new ArrayList();

    public static void main(String[] args){
        System.out.println(jcrypt.crypt("Pk", "12345678"));
        loadAllPasswords(args[1]);
    }

    private static void loadAllPasswords(String pswdFileName){
        String basepath = new File("").getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(basepath + "/src/" + pswdFileName)));
            String st;
            while ((st = br.readLine()) != null) {
                String[] parts = st.split(":");
                unsolvedPasswords.add(new UserEntry(parts[1], parts[4]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found. Make sure the file is in the same directory as PasswordCrack.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class UserEntry {
        public String salt;
        public String hash;
        public String firstName;
        public String surName;

        public UserEntry(String password, String fullName){
            this.salt = password.substring(0,2);
            this.hash = password.substring(2, password.length());
            String[] parts = fullName.split(" ");
            for(int i = 0; i < parts.length; i++) {
                if (this.firstName == null) {
                    this.firstName = parts[i];
                    continue;
                }
                if (this.firstName.contains(".")) {
                    this.firstName = parts[i];
                    continue;
                }
                if (this.surName == null) {
                    this.surName = parts[i];
                    continue;
                }
                if (this.surName.contains(".")) {
                    this.surName = parts[i];
                }
            }
        }
    }
}
