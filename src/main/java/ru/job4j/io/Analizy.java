package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source)); // читает из источника в буфер записывает в файл через буфер и обертку над ним
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
             List<String> list = new ArrayList<>();
             list = findTime(read, list);
             list.stream().forEach(x -> out.write(x));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> findTime(BufferedReader reader, List<String> list) throws Exception {
        String temp = reader.readLine();
        int count = 0;
        boolean flag = false;
        while (temp != null) {
            if (temp.contains("500") || temp.contains("400") && !flag) {
                for (String tmp : temp.split(" ")) {
                    if (count == 1) {
                        list.add(tmp + ";");
                        flag = true;
                        count = 0;
                        continue;
                    }
                    count = 1;
                    continue;
                }

            } else if ((temp.contains("200") || temp.contains("300")) && flag) {
                for (String tmp : temp.split(" ")) {
                    if (count == 1) {
                        list.add(tmp + System.lineSeparator());
                        count = 0;
                        continue;
                    }
                    count = 1;
                    continue;
                }
            }
            temp = reader.readLine();
            if (reader.ready()) {
                while (temp.equals("")) {
                    temp = reader.readLine();
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
