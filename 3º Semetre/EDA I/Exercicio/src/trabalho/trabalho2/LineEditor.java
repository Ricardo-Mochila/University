package trabalho2;

public class LineEditor {

    doubleLinkedList<String> editor = new doubleLinkedList<>();
    int cursor;
    int size;

    public LineEditor(){
        cursor = 0;
        size = 0;
    }

    public void insertEnd(String text){
        editor.add(text);
        size += 1;
        cursor = size;
    }

    public void insert(int n, String text){
        editor.add(n, text);
        cursor = n;
        size += 1;
    }

    public void insert(String text){
        editor.add(cursor, text);
        size += 1;
    }

    public void delete(int n){
        editor.remove(n);
        size -= 1;
        cursor -= 1;
    }

    public void delete(){
        editor.remove(cursor);
        size -= 1;
        cursor -= 1;
    }

    public void edit(int n, String text){
        editor.set(n, text);
        cursor = n;
    }

    public void edit(String text){
        editor.set(cursor,text);
    }

    public String print(){
        return editor.toString()+"Cursor: " + cursor+"\n";
    }

    public String search(String text){
        int c = 1;
        String str = "";

        while(!editor.get(c).elemento.contains(text) && c<size )
        {
            c += 1;
        }
        if (editor.get(c).elemento.contains(text)){
            str += "Line: "+ c + "  "+ "Text: " + editor.get(c).elemento + "\n\n";
            return str;
        }
        return "There is no match";

    }

    public void lineUp(){

        if (cursor > 0){
            cursor -= 1;
        }
    }

    public void lineDown(){
        if (cursor < editor.size()){
            cursor += 1;
        }
    }

    public void porqueSIm(){

        Object a;

        for(int i = 1; editor.iterator().hasNext(); i++){
            a = editor.iterator().next();
            System.out.println(a);
        }

    }

    public static void main(String[] args) {
        LineEditor teste = new LineEditor();

        teste.insertEnd("Bem vindo ao trabalho de EDA");

        teste.insertEnd( "init");
        teste.insertEnd("trabalho2");

        teste.porqueSIm();

        //teste.insertEnd("Escreva o que quiser");
        /*teste.insertEnd("Procure por palavras chave");
        teste.insertEnd("construa um texto");
        teste.insert(4,"Introduza texto a meio");
        teste.edit(2, "Tambem é possível substituirem se linhas");
        System.out.println(teste.print());
        teste.lineDown();
        teste.edit("Substitua onde acabou de adicionar texto");
        System.out.println(teste.print());
        System.out.println(teste.search("construa"));
        teste.delete(1);
        System.out.println(teste.print());*/


    }
}