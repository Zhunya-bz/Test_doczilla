import java.io.*;
import java.nio.file.Files;
import java.util.*;

class Program {

    private static class SortComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String [] str1 = o1.split("/");
            String [] str2 = o2.split("/");

            return str1[str1.length - 1].compareTo(str2[str2.length - 1]);
        }
    }

    public static void main(String[] args) throws IOException {

        final File folder = new File("test");

        ArrayList<String> list = new ArrayList<>();
        search(folder, list); // рекурсивный метод, записывающий путь файла

        Comparator<String> comparator = new SortComparator(); // создаем собственный компоратор для сортировки листа
        list.sort(comparator);

        ArrayList<String> text = new ArrayList<>();
        readfile(list, text); // читаем все файлы и записываем в лист

        FileWriter writer = new FileWriter("result");

        for (int i = 0; i < text.size(); i++) {         // Запись содержимого в файл
            writer.write(text.get(i));
        }
        writer.close();
    }

    public static void readfile(ArrayList<String> list, ArrayList<String> text) throws IOException {

        for (String s : list) {
            String strng = "";
            FileReader fileReader = new FileReader(new File(s));
            BufferedReader reader = new BufferedReader(fileReader);
            String str = "";
            while ((str = reader.readLine()) != null) {
                strng += str;
            }
            text.add(strng);
        }
    }

    public static void search(final File folder, ArrayList<String> result) throws IOException {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                search(fileEntry, result);
            } else {
                result.add(fileEntry.getPath());
            }
        }
    }
}
