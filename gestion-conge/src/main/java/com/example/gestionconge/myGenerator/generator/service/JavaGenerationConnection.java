package generator.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class JavaGenerationConnection {
    static String ip;
    static String user;
    static String port;
    static String database;
    static String password;

    public JavaGenerationConnection(String ip, String user, String port, String database, String password) {
        JavaGenerationConnection.ip = ip;
        JavaGenerationConnection.user = user;
        JavaGenerationConnection.port = port;
        JavaGenerationConnection.database = database;
        JavaGenerationConnection.password = password;
    }

    public static String write() {
        loadConfigFromXML();
        String content = "spring.datasource.url=jdbc:postgresql://"+ip+":"+port+"/"+database+"\n";
        content += "spring.datasource.username="+user+"\n";
        content += "spring.datasource.password="+password+"\n";
        content += "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect\n";
        content += "server.port=8080\n";
        content += "spring.mvc.view.prefix=/WEB-INF/views/\n";
        content += "spring.mvc.view.suffix=.jsp";
        return content;
    }

    public static void loadConfigFromXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("./db_connection.xml");

            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    switch (element.getTagName()) {
                        case "ip":
                            JavaGenerationConnection.ip = element.getTextContent();
                            break;
                        case "port":
                            JavaGenerationConnection.port = element.getTextContent();
                            break;
                        case "database":
                            JavaGenerationConnection.database = element.getTextContent();
                            break;
                        case "user":
                            JavaGenerationConnection.user = element.getTextContent();
                            break;
                        case "password":
                            JavaGenerationConnection.password = element.getTextContent();
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
