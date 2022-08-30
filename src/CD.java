import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD
{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    CD(String path)
    {
        run(path);
    }
    CD() throws IOException
    {
        System.out.print("Please enter dir path: ");
        String path = in.readLine();
        run(path);
    }
    private static void run(String path)
    {
        Main.currDir = new File(path);

        if(!Main.currDir.isDirectory())
        {
            File check = new File(Main.elements.item(1).getTextContent() + "\\" + path);
            if(check.isDirectory() && check.exists())
            {
                Main.currDir = check;
                Main.elements.item(1).setTextContent(check.getAbsolutePath());
            }
            else
            {
                Main.currDir = new File(Main.elements.item(1).getTextContent());
                System.out.println("No such dir found.");
            }
        }
        else
        {
            Main.elements.item(1).setTextContent(path);
        }
    }
}
