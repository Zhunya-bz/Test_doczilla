import java.io.*;
import java.util.*;

class Program {
    public static void main(String[] args) throws IOException {

        final File folder = new File(".");
//        System.out.println(folder.listFiles());

        ArrayList<String> pathFile = new ArrayList<>();
        TreeMap<String, String> tm = new TreeMap<>();

        search(folder, tm); // рекурсивный метод, записывающий путь и имя файла в TreeMap
//        System.out.println(tm);

        SortedSet<Map.Entry<String, String>> so = entriesSortedByValues(tm); // метод, сортирующий по значению
//        System.out.println(so);

        ArrayList<String> text = new ArrayList<>();
        readfile(pathFile, so, text); // читаем все файлы и записываем в лист

        System.out.println(text);

        FileWriter writer = new FileWriter("result");

        // Запись содержимого в файл
        for (int i = 0; i < text.size(); i++) {
            writer.write(text.get(i));
        }
        writer.close();

    }

    static <K,V extends Comparable<? super V>> // взято из гугла, тут происходит сортировка TreeMap по значению
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    public static void readfile(ArrayList<String> result, SortedSet<Map.Entry<String, String>> tm, ArrayList<String> text) throws IOException {

        for (Map.Entry<String, String> pair : tm) {
            System.out.println(pair.getKey());
            String strng = "";
            FileReader fileReader = new FileReader(new File(pair.getKey()));
            BufferedReader reader = new BufferedReader(fileReader);
            String str = "";
            while ((str = reader.readLine()) != null) {
                strng += str;
            }
            text.add(strng);
        }

    }

    public static void search(final File folder, TreeMap<String, String> result) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                search(fileEntry, result);
            } else {
//                System.out.println(fileEntry.toString().lastIndexOf("."));
                if (fileEntry.isFile() && (fileEntry.toString().contains("txt") || fileEntry.toString().lastIndexOf(".") == 0))
                    result.put(fileEntry.getPath(), fileEntry.getName());
            }
        }
    }
}
