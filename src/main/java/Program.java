import com.sun.net.httpserver.Filter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Program {
    public static void main(String[] args) throws IOException {

        final File folder = new File("../resources");

        ArrayList<String> pathFile = new ArrayList<>();

        search(folder, pathFile);
        System.out.println(pathFile);

        ArrayList<String> text = new ArrayList<>();
        readfile(pathFile, text);

//        for (int i = 0; i < pathFile.size(); i++) {
//            String str = pathFile.get(i).replace("../resources/", "");
//            pathFile.set(i, str);
////            System.out.println(pathFile.get(i));
//        }
//        System.out.println(pathFile);
//
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < text.size(); i++) {
//            if (text.get(i).contains("require \'")) {
//                int ind = text.get(i).indexOf("require \'");
//                ind += 9;
//                int start = ind;
//
//                while (text.get(i).charAt(ind) != '\'' ) {
//                    ind++;
//                }
//                String str = text.get(i).substring(start, ind);
//                System.out.println(str);
//                if (!str.isEmpty())
//                    list.add(str);
//                for (int j = 0; j < list.size(); j++) {
//                    if (!str.equals(pathFile.get(i))) {
//                        list.add(0, pathFile.get(i));
//                    } else {
//                        System.out.println("Error");
//                    }
//                }
//
//            }
//        }
//        for (int i = 0; i < text.size(); i++) {
//
//        }
//
//
//        System.out.println(list);



    }

    public static void readfile(ArrayList<String> result,  ArrayList<String> text) throws IOException {
        for (int i = 0; i < result.size(); i++) {
            String strng = "";
            FileReader fileReader = new FileReader(new File(result.get(i)));
            BufferedReader reader = new BufferedReader(fileReader);
            String str = "";
            while ((str = reader.readLine()) != null) {
                strng += str;
            }
            text.add(strng);
        }

//        System.out.println("size" + text.size());
//        System.out.println("0: " + text.get(0));
//        System.out.println("1: " +text.get(1));
//        System.out.println("2: " +text.get(2));
    }

    public static void search(final File folder, ArrayList<String> result) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                search(fileEntry, result);
            } else {
                result.add(fileEntry.getPath());
            }
        }
    }
}





//        for (String s : result) {
//                System.out.println(s);
//                }
//
//                }
//
//public static void search(final String pattern, final File folder, List result) {
//        for (final File f : folder.listFiles()) {
//
//        if (f.isDirectory()) {
//        search(pattern, f, result);
//        }
//
//        if (f.isFile()) {
//        if (f.getName().matches(pattern)) {
//        result.add(f.getAbsolutePath());
//        }
//        }
//
//        }
//        }