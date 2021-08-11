import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formats {


    public static void main(String[] args) {

        //если переданное формату число в промежутке (-inf; 10d), то формат подставит choiceData[0]->{2}->data[2]->QQQ
        //если переданное формату число в промежутке [10d; 20d), то формат подставит choiceData[1]->MMMMMM
        //если переданное формату число в промежутке [20d; +inf), то формат подставит choiceData[2]->{4}->data[4]->ZZZ
        double[] limits = {0d, 10d, 20d};
        String[] choiceData = {"{2}", "MMMMMM", "{4}"};
        ChoiceFormat choiceFormat = new ChoiceFormat(limits, choiceData);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        Format[] formats = {null, choiceFormat, dateFormat, choiceFormat};


        //вместо {idx} будут подставлены данные из data[idx]
        //данные из data[idx] будут форматированы форматом из formats[порядковый номер индекса-плэйсхолдера (не число внутри {}, а номер по порядку появления в строке)]
        MessageFormat mesFormat = new MessageFormat("{0} aaa {1} fff {5} bbb {3} ccc {6} ddd {2} eee {1}");
        //format[0]->null применится к data[0]->HHH, т.к. формат==null, то значение из data просто подставится
        //format[1]->choiceFormat применится к data[1]->20d, choiceFormat выберет choiceData[2]->data[4]->"ZZZ"
        //format[2]->dateFormat применится к data[5]->new Date()
        //format[3]->choiceFormat применится к data[5]->15d, choiceFormat выберет choiceData[1]->"MMMMMM"
        //format[4] не существует, data[6] не существует, так что {6} ничем не заменится
        //format[5] не существует, просто подставится data[2]->QQQ
        //format[6] не существует, просто подставится data[1]->20


        mesFormat.setFormats(formats);


        //{0}{1}{2}{3}{4}{5} - обозначение индексов в формата
        //{idx} -> data[idx]
        Object[] data = {"HHH", 20d, "QQQ", 15d, "ZZZ", new Date()};


        System.out.println(mesFormat.format(data));

    }

}

