import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main (String[]args){
        int rodadas = 0; //nro de rodadas
        int countLinhas = 0; 
        int countRodadas=0; 
        int macacoPar = 0;
        int macacoImpar = 0;       
        ArrayList<ArrayList<Integer>> matriz = new ArrayList<ArrayList<Integer>>();       
        ArrayList<String> linhas = new ArrayList<>(); //linhas da matriz
        String palavras[]; //palavras da linha
        BufferedReader reader;

        Path path1 = Paths.get("macacoTeste.txt");
        //carrega as linhas
        try {
            reader = Files.newBufferedReader(path1, Charset.defaultCharset());
            String line = null;
            while( (line = reader.readLine()) != null) {
                linhas.add(line);
                
            }
            //for(String l : linhas){
                //System.out.println(l);
            //}

            for(String l : linhas){ //percorre as linhas
                palavras = l.split(" "); // divide a string pelo espaco em branco

                //for(int i=0; i<palavras.length; i++){
                //    System.out.println(palavras[i]);
                //}
                if (countLinhas==0){ //primeira linha, rodadas a fazer
                    for(int i =0; i< palavras.length; i++) {
                        String teste= palavras[i] + "";
                        if (teste.matches("[0-9]+")){
                            rodadas = Integer.parseInt(teste);
                        }
                    }
                }
                else{ //se não for a primeira linha
                    matriz.add(new ArrayList<Integer>()); 
                    for(int i=0; i < palavras.length; i++){
                        String test= palavras[i] + "";                    
                        if(test!=""){  //se string não estiver vazia                        
                            if(test.matches("[0-9]+")){  //se string for número, adiciona na lista                            
                                matriz.get(countLinhas-1).add(Integer.parseInt(test));
                            }
                        }
                    }
                }
                countLinhas++;
            }            

            //System.out.println("RODADAS: "+rodadas+"\n");
            //System.out.println("MATRIZ");
            //for(int i=0; i<matriz.size(); i++){
                //for(int j=0; j<matriz.get(i).size(); j++){
                    //System.out.print(matriz.get(i).get(j) + "  ");
                //}
                //System.out.println();
            //}
            
            reader.close();

        } catch (Exception e) {
            System.err.format("Erro na leitura do arquivo ", e);
        }

        while(countRodadas<rodadas){ //enquanto o contador for menor que o número de rodadas
            //System.out.println("rodada "+countRodadas);
            for(int i=0; i<matriz.size(); i++){              
                for(int j=0; j<matriz.get(i).size(); j++){                                     
                    if(j==0){
                        //não faz nada ??                      
                    }
                    else if(j==1){
                        //macaco par
                        macacoPar = matriz.get(i).get(j); 
                    }
                    else if(j==2){
                        //macaco impar
                        macacoImpar = matriz.get(i).get(j);
                    }
                    else { //senão distribui os números
                        if(par(matriz.get(i).get(j))==true){
                            //adiciona elemento no macaco par
                            matriz.get(macacoPar).add(matriz.get(i).get(j));                           
                        }
                        else{
                            //adiciona elemento no macaco impar
                            matriz.get(macacoImpar).add(matriz.get(i).get(j));                            
                        }                       
                    }                     
                }
                //faz a remoção do índice 3 até o array ficar com tamanho = 2
                while(matriz.get(i).size()>3){
                    matriz.get(i).remove(3);                  
                }
                    
            }
            //for(int i=0; i<matriz.size(); i++){
                //for(int j=0; j<matriz.get(i).size(); j++){
                    //System.out.print(matriz.get(i).get(j) + "  ");
                //}
                //System.out.println();
            //}
            countRodadas++;
        }

        //System.out.println("MATRIZ FINAL");
            //for(int i=0; i<matriz.size(); i++){
                //for(int j=0; j<matriz.get(i).size(); j++){
                    //System.out.print(matriz.get(i).get(j) + "  ");
                //}
                //System.out.println();
            //}

        //verificar qual macaco tem mais cocos (o maior array)
        System.out.println("Ganhador: macaco "+ganhador(matriz));
        
    }

    public static int ganhador(ArrayList<ArrayList<Integer>> matriz){
        int tamanho = 0;
        int macaco = 0;
        for(int i=0; i<matriz.size(); i++){
            if(matriz.get(i).size()>tamanho){
                tamanho = matriz.get(i).size();
                macaco = i;
            }         
        }
        return macaco;
    }

    public static boolean par(int num){
        boolean par = false;              
        if (num%2==0){ par = true;}              
        return par;
    }  
}