import java.io.File;

public class DIR
{
    DIR(String arg)
    {
        System.out.println("Unknown argument");
    }
    DIR()
    {
        run();
    }
    private void run()
    {
        File[] list = Main.currDir.listFiles();
        for(File s : list)
        {
            System.out.println(s.getName());
        }
    }
}
