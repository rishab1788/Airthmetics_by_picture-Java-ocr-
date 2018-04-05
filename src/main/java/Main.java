import org.bytedeco.javacpp.*;

import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;

public class Main {
    public static void main(String[] args) {
        BytePointer outText;

        TessBaseAPI api = new TessBaseAPI();


        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(null, "eng" ) != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }



        // Open input image with leptonica library
        PIX image = pixRead(args.length > 0 ? args[0] : "D:\\keep.jpg");
        api.SetImage(image);

        // Get OCR result
        outText = api.GetUTF8Text();
        String s=outText.getString();

        s = s.trim().replaceAll("\n", "");

        s = s.trim().replaceAll(" ", "");
        System.out.print("OCR output:\n   " + s+"=");

        char str[]=s.toCharArray();
int c=0;
String f="";
String ss="";int t=0;


for (int i=0;i<s.length();i++)
{t++;
    if (str[i]=='+'||str[i]=='-'||str[i]=='/'||str[i]=='*')
    {
        break;
    }
    f+=str[i];
}
        for (int i=t;i<s.length();i++)
        {ss+=str[i];t++;
        }


int ff=Integer.parseInt(f);
int sss=Integer.parseInt(ss);
        char op;
        if (s.contains("+"))
        {op='+';
System.out.print(ff+sss);

        }
        else if (s.contains("-"))
        {op='-';

            System.out.print(+ff-sss);
        }
        else if (s.contains("/"))
        {op='/';

            System.out.print(ff/sss);}
        else if (s.contains("*"))
        {
            System.out.print(ff*sss);}



        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
    }
}