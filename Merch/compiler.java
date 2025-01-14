import java.io.*;
import java.util.Vector;

class Main {

    public static void main(String[] args) {

        String template = "template.spec";

        String[] targets = { "eureka", "eureka", "eureka" };

        String header = "header.spec";
        String repeatStructure = "repeat.spec";
        String footer = "footer.spec";

        Vector<String> h = read(header);
        Vector<String> r = read(repeatStructure);
        Vector<String> f = read(footer);

        Vector<String> ind_buf = new Vector<>(1,1);
        ind_buf = join(ind_buf, h);

        for(String target : targets) {
            Vector<String> buf = new Vector<>(1,1);
            buf = join(buf, r);

            Vector<String> content = read(target+".txt");

            String title = content.elementAt(0).trim();
            String author = content.elementAt(1).trim();
            String publish_date = content.elementAt(2).trim();

            buf = replace(buf, "{{ title }}", title);
            buf = replace(buf, "{{ author }}", author);
            buf = replace(buf, "{{ date }}", publish_date);
            buf = replace(buf, "{{ img_url }}", ""+target+".jpeg");
            buf = replace(buf, "{{ link }}", target+".html");

            ind_buf = join(ind_buf, buf);
        }

        ind_buf = join(ind_buf, f);
        write("index_blog_gen.html", ind_buf);

        for(String target : targets) {
            Vector<String> t = read(template);
            Vector<String> content = read(target+".txt");

            String title = content.elementAt(0).trim();
            String author = content.elementAt(1).trim();
            String publish_date = content.elementAt(2).trim();

            t = replace(t, "{{ title }}", title);
            t = replace(t, "{{ author }}", author);
            t = replace(t, "{{ date }}", publish_date);

            Vector<String> buffer = new Vector<>(1,1);
            int i;
            for(i = 0; i < t.size(); i++) {
                if(t.elementAt(i).trim().equals("{{ content }}")) break;
                buffer.addElement(t.elementAt(i));
            }

            for(int j = 3; j < content.size(); j++) 
                buffer.addElement(content.elementAt(j)+"<br>");

            for(i = i + 1; i < t.size(); i++) buffer.addElement(t.elementAt(i));

            write(target+".html", buffer);
        }

    }

    public static Vector<String> read(String fn) {
        Vector<String> vec = new Vector<>(1,1);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fn));
            while(true) {
                String s = br.readLine();
                if(s == null) break;
                vec.addElement(s);
            }
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return vec;
    }

    public static void write(String fn, Vector<String> data) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fn));
            for(String st : data) bw.write(st + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector<String> replace(Vector<String> vector, String target, String replacement) {
        Vector<String> v = new Vector<>(1,1);
        for(int i = 0; i < vector.size(); i++) v.addElement(vector.elementAt(i).replace(target, replacement));
        return v;
    }

    public static Vector<String> join(Vector<String> v1, Vector<String> v2) {
        Vector<String> v = new Vector<>(1,1);
        for(String s : v1) v.addElement(s);
        for(String s : v2) v.addElement(s);
        return v;
    }

}