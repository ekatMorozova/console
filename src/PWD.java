public class PWD
{
    PWD(String arg)
    {
        System.out.println("Unknown argument");
    }

    PWD()
    {
        run();
    }

    public void run()
    {
        System.out.println("Curr dir path: " + Main.currDir.getAbsolutePath());
    }
}
