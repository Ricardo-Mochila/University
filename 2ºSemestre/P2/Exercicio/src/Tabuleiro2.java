    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.*;
    public class Tabuleiro2{
        int[][] tabuleiro;
        boolean[][] apagar;
        private int tamanho, cores;
        private boolean possivel;
        private JFrame jframe = new JFrame();
        private JButton botao = new JButton();

        //Método que dá as varáveis ao método "preencherTabuleiro".
        public Tabuleiro2( int tamanho, int cores){
            this.tamanho = tamanho;
            this.cores = cores;
            tabuleiro = new int[tamanho+2][tamanho+2];
            preencherTabuleiro(cores);
        }

        //Método que enche o tabuleiro e cria a fronteira.
        public void preencherTabuleiro(int cores){
            Random r = new Random(cores);
            jframe.setTitle("Fusion");
            jframe.setLayout(new GridLayout(tamanho,tamanho,5,5));
            jframe.setVisible(true);
            jframe.setSize(500,500);

            for (int i=0; i<tamanho; i++){
                for(int j=0; j<tamanho; j++){
                    int cor = r.nextInt(cores);
                    tabuleiro[i][j] = cor;
                    botao = new JButton(String.valueOf(cor));
                    graphics(cor,botao);
                    jframe.add(botao);



                }
            }
        }
        //Método que dá a cor ao tabuleiro
        public void graphics(int numeros, JButton botao){
            if (numeros == 1){
                botao.setBackground(Color.red);

            }else if (numeros == 2){
                botao.setBackground(Color.green);
            }else if (numeros == 3){
                botao.setBackground(Color.yellow);

            }else if (numeros == 4){
                botao.setBackground(Color.blue);

            }else if (numeros == 5){
                botao.setBackground(Color.pink);

            }else if (numeros == 6){
                botao.setBackground(Color.orange);

            }else if (numeros == 7){
                botao.setBackground(Color.magenta);

            }else if (numeros == 8){
                botao.setBackground(Color.cyan);

            }else if (numeros == 9){
                botao.setBackground(Color.gray);

            }
        }

        public abstract class Action implements ActionListener{
            public void actionPerformed(ActionEvent e) {



            }
        }




        //Método que verifica se o jogo continua ou termina.
        public boolean  condicao(){
            for(int i=1; i<=tabuleiro.length-1; i++){
                for( int j=1; j<=tabuleiro.length-1; j++){
                    if(tabuleiro[i][j] != 9 && tabuleiro[i][j] != cores+1){ //Não verifica as peças "eliminadas" nem a fronteira.
                        if(tabuleiro[i][j] == tabuleiro[i+1][j] || tabuleiro[i][j] == tabuleiro[i][j+1]){
                            return true;
                        }
                    }
                }

            }

            int pontos = (tabuleiro.length-2) * (tabuleiro.length-2) - contarPecas();
            System.out.println("Não há mais jogadas disponíveis! \n "
                    + "a  sua pontuação é de:" + pontos);
            return false;  //Não há mais jogadas, retorna a pontuação do jogador.
        }

        //Método que permite a jogada.
        public void jogada(int x, int y){
            if(tabuleiro[x][y] == 9){		//Invalida a jogada caso seja numa casa já "eliminada".
                System.out.println("input inválido, tente de novo.");
                return;
            }
            apagar = new boolean[tamanho+2][tamanho+2]; //Sempre que se joga cria-se um arraio "apagar" novo.
            apagar[x][y] = true;
            possivel = false;
            vizinhos(x,y);
            if(!possivel){		//Se não houver vizinhanças, return.
                System.out.println("Não há vizinhanças onde quer jogar, tente novamente.");
                return;
            }
            for(int i=1; i<tabuleiro.length-1; i++){
                for( int j=1; j<tabuleiro.length-1; j++){
                    if(apagar[i][j]){
                        tabuleiro[i][j] = 9;		//Nas posições de "apagar" marcadas com true, o valor do "tabuleiro muda para 9, como se fosse apagado.
                    }
                }
            }
            paraBaixo();
            paraDireita();
        }


        //Método que verifica se as coordenadas selccionadas estão aptas para uma jogada.
        public void vizinhos(int x, int y) {


            /*for(int i = 0; i!=tabuleiro.length; i++){
                for(int j = 0; j!=tabuleiro.length;j++){
                    for(int x1 = -1; x1!=1;x1++){
                        for(int y1=-1;y1!=1;y1++){
                            x=i+x1;
                            y=j+y1;
                        }
                    }
                    possivel = true;
                    apagar[x][y]=true;

                }
            }*/




            if (tabuleiro[x][y] == tabuleiro[x - 1][y] && !apagar[x - 1][y]) { //Verifica em cima.
                possivel = true;
                apagar[x - 1][y] = true;
                vizinhos(x - 1, y);
            }
            if (tabuleiro[x][y] == tabuleiro[x + 1][y] && !apagar[x + 1][y]) {  //Verifica em baixo.
                possivel = true;
                apagar[x + 1][y] = true;
                vizinhos(x + 1, y);

            }
            if (tabuleiro[x][y] == tabuleiro[x][y - 1] && !apagar[x][y - 1]) {  //Verifica à esquerda.
                possivel = true;
                apagar[x][y - 1] = true;
                vizinhos(x, y - 1);

            }
            if (tabuleiro[x][y] == tabuleiro[x][y + 1] && !apagar[x][y + 1]) {  //Verifica à direita.
                possivel = true;
                apagar[x][y + 1] = true;
                vizinhos(x, y + 1);

            }
        }

        //Método que puxa os valores ainda jogáveis para baixo.
        public void paraBaixo(){
            for (int i=1; i<tabuleiro.length-1; i++){  //Percorre as linhas.
                for(int j=1; j<tabuleiro.length-1; j++){ //Percorre as colunas
                    if(tabuleiro[i][j] == 9){		//Se encontrar um nove troca-o com os valores de cima até chegar a um  nove ou até chegar à primeira linha.
                        for(int i2 = i; i2>1; i2--){
                            if(tabuleiro[i2][j-1] != 9 && tabuleiro[i2][j-1] != tamanho+1){
                                tabuleiro[i2][j] = tabuleiro[i2-1][j];
                                tabuleiro[i2-1][j] = 9;
                            }
                        }
                    }
                }
            }
        }

        //Método que permite a deslocação de uma coluna vazia para a direita
        public void paraDireita(){
            for(int j=1; j<tabuleiro.length-1; j++){   //Percorre a última fila
                if(tabuleiro[tamanho][j] == 9){			   // Se encontrar um nove troca-o sucessivamente de posição para a esquerda
                    for(int i2 = tamanho; i2>=1; i2--){
                        for(int j2 = j; j2>1; j2--){
                            if(tabuleiro[i2][j2-1] != 9){
                                tabuleiro[i2][j2] = tabuleiro[i2][j2-1];
                                tabuleiro[i2][j2-1] = 9;
                            }
                        }
                    }
                }
            }
        }

        //Função que irá contar as peças que sobram no caso de não haver mais jogadas disponíveis.
        public int contarPecas(){
            int conta = 0;
            for(int i=1; i<tabuleiro.length-1; i++){	//Percorre o tabuleiro juntamente com a fun��o de baixo
                for( int j=1; j<tabuleiro.length-1; j++){	//� procura de peças válidas.
                    if(tabuleiro[i][j]!=9){
                        conta++; //Adiciona as peças uma a uma ao contador.
                    }
                }
            }
            return conta;
        }

        //Método que cria e mostra o tabuleiro.
        public void printTab(){
            for (int i=0; i<tamanho; i++){
                for(int j=0; j<tamanho; j++){
                    System.out.print(tabuleiro[i][j]+ " ");

                }
                System.out.println("");

            }
        }

        //Método main, permite que o programa corra.
        public static void main(String[] args){
            Scanner scan = new Scanner(System.in);
            int tamanho, cores, x, y;

            System.out.println("Qual o Tamanho do tabuleiro?"+"\n"+ "(inserir inteiro)");
            tamanho = scan.nextInt();

            System.out.println("Qual a numero de cores que quer jogar?"+"\n"+"(introduzir inteiro entre 2 e 9)");
            cores = scan.nextInt();

            //System.out.println("Qual a seed que quer jogar?"+"\n"+"(introduzir inteiro)");
            //while(!scan.hasNextInt()){
            //    System.out.println("Input inválido."+"\n"+"(introduzir inteiro)");
            //    scan.nextLine();
            //}
            //S = scan.nextInt();//

            Tabuleiro2 Teste = new Tabuleiro2(tamanho, cores);

            Teste.printTab();

            while(Teste.condicao()){
                do{System.out.println("Introduzir coordenadas. (x y)");
                    y = scan.nextInt();
                    x = scan.nextInt();
                    if(x>tamanho || y>tamanho){
                        System.out.println("Input inválido. Tente de novo.");
                    }
                }while(x>tamanho || y>tamanho);
                Teste.jogada(x,y);
                Teste.printTab();
            }
            scan.close();
        }

    }