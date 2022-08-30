import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Main
{
    public static File currDir;
    public static NodeList elements;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("commands.xml");
        Node root = document.getDocumentElement();
        elements = root.getChildNodes();
        Node cElements = elements.item(3);
        NodeList commands = cElements.getChildNodes();
        currDir = new File(elements.item(1).getTextContent());

        String str;

        while (true)
        {
            System.out.print(elements.item(1).getTextContent() + " >>");
            str = in.readLine();

            String[] input = str.split(" ", 2);

            for (int i = 0; i < commands.getLength(); i++)
            {
                if (commands.item(i).getNodeType() != Node.TEXT_NODE)
                {
                    Node currCommand = commands.item(i);

                    ArrayList<String> params = new ArrayList<>();

                    for (int j = 0; j < currCommand.getChildNodes().getLength(); j++)
                    {
                        if (currCommand.getChildNodes().item(j).getNodeType() != Node.TEXT_NODE)
                        {
                            params.add(currCommand.getChildNodes().item(j).getTextContent());
                        }
                    }

                    if (input[0].equals(params.get(0)))
                    {
                        try
                        {
                            if (input.length > 1)
                                invoke(params.get(1), input[1]);
                            else
                                invoke(params.get(1));
                        }
                        catch (Exception e)
                        {
                            System.err.println(e.fillInStackTrace());
                        }
                    }
                }
            }
        }
    }

    public static void invoke(String commandName, String commArgs) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Class[] args = new Class[1];
        args[0] = String.class;
        Class Command = Class.forName(commandName);
        Command.getDeclaredConstructor(args).newInstance(commArgs);
    }

    public static void invoke(String commandName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Class Command = Class.forName(commandName);
        Command.getDeclaredConstructor().newInstance();
    }
}