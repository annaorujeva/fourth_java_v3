package app.repository;

import app.domain.Account;
import app.store.StoreAccounts;
import java.io.*;

public class FileRepository implements Repository {
    public String line;
    public int acc_id;
    public int acc_amount;
    public String acc_holder;
    public String[] data;
    public static String directory = "C:/Users/Anna/lr4_rv/";

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }

    public void write(Account accounts) {
        try (FileWriter fw = new FileWriter(directory + accounts.getId() + ".txt")){
            fw.write(accounts.getId()+" " + accounts.getAmount() + " "+ accounts.getHolder());
            fw.flush();
        }
        catch (IOException ex){
            System.out.println("Не удалось сохранить файл" + ex.getMessage());
        }
    }

    @Override
    public void read() {
            try {
                File file = new File(directory);
                File[] listofFiles = file.listFiles();
                for (int i = 0; i < listofFiles.length; i++) {
                    File infile = new File(directory + listofFiles[i].getName());
                    FileReader fileReader = new FileReader(infile);
                    BufferedReader reader = new BufferedReader(fileReader);
                    line = reader.readLine();
                    data = line.split(" ");
                    acc_id = Integer.parseInt(data[0]);
                    acc_amount = (int) Float.parseFloat(data[1]);
                    acc_holder = data[2];
                    Account user = new Account(acc_id, acc_holder, acc_amount);
                    StoreAccounts.accounts.add(user);
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    public boolean isEmptyDirectory(){
        File dir = new File(directory);
        String files[] = dir.list();
        return files.length == 0;
    }

    public boolean isDirectoryExists(){
        File dir = new File(directory);
        if (dir.exists()){
            System.out.println("Директория существует");
            return true;
        }
        else return false;
    }

    public void createDirectory(){
        File dir = new File(directory);
        if(isDirectoryExists()==false){
            dir.mkdir();
            System.out.println("Директория создана");
        }
    }

    public void GenerateDir(){
        for (int i =1; i<11; i++){
            Account user = new Account(i, "holder" + i, 1000*i);
            StoreAccounts.accounts.add(user);
            write(user);
        }
        for (int i=0;i<StoreAccounts.accounts.size();i++){
            System.out.println(StoreAccounts.accounts.get(i).getId()+" "+StoreAccounts.accounts.get(i).getAmount()+" "+StoreAccounts.accounts.get(i).getHolder());
        }
        System.out.println("Директория заполнена");
    }

//    public void startofwork() {
//        if (isDirectoryExists(directory)) {
//            if (isEmptyDirectory(directory)) {
//                fillDirectory();
//            }
//            else {
//                read();
//            }
//        } else {
//            createDirectory(directory);
//            fillDirectory();
//        }
//    }
}
