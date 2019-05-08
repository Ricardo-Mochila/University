/*import java.util.ArrayList;

public class Ficha2 <T> implements FilaDeEspera<T>, Comparable<FilaDeEspera<?>>{

    ArrayList<T> lista = new ArrayList<>();
    int size;

    @Override
    public int size() {
        return lista.size();
    }

    @Override
    public int maxSize() {
        return size;
    }

    @Override
    public int places() {
        return size-lista.size();
    }

    @Override
    public boolean isFull() {

        if(lista.size() >= size) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if(lista.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void add(T name) {

        lista.add(name);
    }

    @Override
    public T front() {
        return lista.get(0);
    }

    @Override
    public T remove() {
        return lista.remove(0);
    }

    @Override
    public String list() {
        String listacompleta = "";
        for(int j = 0; j <lista.size(); j++){
            listacompleta += lista.get(j);
        }
        return listacompleta;
    }

    public Ficha2(){
        size = 10;
    }

    public Ficha2(int a){
        size = a;
    }

    public static void main(String[] args) {

        Ficha2<String> f = new Ficha2<>();
        f.add("Inês");
        f.add("Joaquim");
        f.add("Ricardo");
        f.add("Pedro");
        f.add("Alfabeto");
        f.add("Joaquina");

        System.out.println("Tamanho "+ f.size() +  "; Tamanho maximo: " + f.maxSize() + "; vagas: "+ f.places()+ "; Está cheio?: "+f.isFull()+"; Está Vazio?: "+ f.isEmpty()+"; Primeiro nome: "+f.front()+"; Remover O primeiro: "+f.remove());
        System.out.println(f.front());
        System.out.println(f.lista);
    }


    @Override
    public int compareTo(FilaDeEspera<?> o) {

        Ficha2 f1=(Ficha2) o;
        float x = this.size();
        float y = o.size();
        return 0;
    }
}*/
