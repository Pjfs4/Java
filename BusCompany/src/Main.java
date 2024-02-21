import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    final static String PLANEAMENTO = "testcase1in.txt";
    final static String[] CIDADES = cidades();
    final static int[][] DISTANCIAS = tabelasDistancias();

    public static void main(String[] args) throws FileNotFoundException {

        int[][] infoPlaneamento = lerPlaneamento();
        System.out.println("b)");
        visualizarPlaneamentoViagem(infoPlaneamento);

        int[][] matrizKm = kmPercorridosDiariamenteDoAutocarro(infoPlaneamento);
        kmPercorridosDiariamenteDoAutocarro(infoPlaneamento);
        System.out.println("c)");
        visualizarPlaneamentoViagem(matrizKm);

        //visualizarKmPercorridos(matrizKm);

        int[] kmMaxPorAutocarro = calcularTotalDeKmPercorridosPorAutocarro(matrizKm);
        System.out.println("d)");
        visualizarTotalDeKmPercorridosPorAutocarro(matrizKm, kmMaxPorAutocarro);

        int kmTotais = calcularTotaldeKmPercorridosPelaFrotaDeAutocarros(matrizKm);
        System.out.println("e)");
        visualizarTotaldeKmPercorridosPelaFrotaDeAutocarros(kmTotais);


        int[] kmMaxPorDia = matrizkmMaxPorDia(matrizKm);
        int kmMax = kmMaxPorDia(kmMaxPorDia);
        String diasComMaisKm = diasComMaisKm(kmMaxPorDia,kmMax);
        System.out.println("f)");
        visualizarDiasComMaisKm(kmMax,diasComMaisKm);

        System.out.println("g)");
        visualizarAutocarrosQuePermanecemMaisDeUmDiaNoMesmoSitio(infoPlaneamento);

        System.out.println("h)");
        emQueDiaOsAutocarrosNaMesmaCidade(infoPlaneamento);

        double[] percentagem = calcularPercentagem(infoPlaneamento, kmMaxPorAutocarro, kmTotais);
        System.out.println("i)");
        visualizarPercentagemDeKm(matrizKm, percentagem, kmTotais);

        int[] autocarroEDia = lerAutocarroEDia();
        System.out.println("j)");
        visualizarAutocarroMaisProximo(infoPlaneamento,autocarroEDia);

    }

    //Visualizar a Matriz Distâncias
    public static int[][] tabelasDistancias() {

        int[][] tabelasDistancias = {
                {0, 50, 60, 130, 300, 200},
                {50, 0, 130, 70, 250, 140},
                {60, 130, 0, 180, 370, 250},
                {130, 70, 180, 0, 200, 90},
                {300, 250, 370, 200, 0, 130},
                {200, 140, 250, 90, 130, 0}
        };

        return tabelasDistancias;


    }

    //Visualizar a Matriz Cidades
    public static String[] cidades() {

        String[] cidades = {"Porto", "Aveiro", "Braga", "Coimbra", "Lisboa", "Fátima"};

        return cidades;

    }



    //a) Leitura da matriz a partir do ficheiro
    public static int[][] lerPlaneamento() throws FileNotFoundException {

        Scanner ler = new Scanner(new File(PLANEAMENTO));
        ler.nextLine();

        int nAutocarros = ler.nextInt();
        int diasViagem = ler.nextInt();
        int[][] planeamentoViagem = new int[nAutocarros][diasViagem];

        for (int autocarros = 0; autocarros < planeamentoViagem.length; autocarros++) {

            for (int dia = 0; dia < planeamentoViagem[autocarros].length; dia++) {

                planeamentoViagem[autocarros][dia] = ler.nextInt();

            }

        }

        ler.close();

        return planeamentoViagem;

    }
    //b) Visualizar a matriz do planeamento da viagem
    public static void visualizarPlaneamentoViagem(int[][] planeamentoViagem){

        for (int autocarro = 0; autocarro < planeamentoViagem.length; autocarro++) {

            System.out.print("Bus" + autocarro + " : ");

            for (int dia = 0; dia < planeamentoViagem[autocarro].length; dia++) {

                System.out.printf("%4d", planeamentoViagem[autocarro][dia]);

            }

            System.out.println();

        }

        System.out.println();

    }

    //c1) Criar a matriz de km percorridos por dia
    public static int[][] kmPercorridosDiariamenteDoAutocarro(int[][] planeamentoViagem){

        int[][] kmPercorridosPorDia = new int[planeamentoViagem.length][planeamentoViagem[0].length];

        for (int autocarro = 0; autocarro < planeamentoViagem.length; autocarro++) {

            for (int dia = 1; dia < planeamentoViagem[autocarro].length; dia++) {

                int cidadeAtual = planeamentoViagem[autocarro][dia - 1];
                int cidadeSeguinte = planeamentoViagem[autocarro][dia];

                kmPercorridosPorDia[autocarro][dia] = DISTANCIAS[cidadeAtual][cidadeSeguinte];

            }

        }

        return kmPercorridosPorDia;

    }

    //d1) Calcular o total de km percorridos por autocarro
    public static int[] calcularTotalDeKmPercorridosPorAutocarro(int[][] planeamentoViagem) {

        int[] soma = new int[planeamentoViagem.length];

        for (int autocarro = 0; autocarro < planeamentoViagem.length; autocarro++) {

            for (int dia = 0; dia < planeamentoViagem[autocarro].length; dia++) {

                soma[autocarro] += planeamentoViagem[autocarro][dia];

            }

        }

        return soma;

    }
    //d2) Visualizar a matriz km percorridos por autocarro
    public static void visualizarTotalDeKmPercorridosPorAutocarro(int[][] matrizKm, int[] kmMax) {

        for (int autocarro = 0; autocarro < matrizKm.length; autocarro++) {

            System.out.println("Bus" + autocarro + " : " + kmMax[autocarro] + " km");

        }

        System.out.println();

    }



    //e1) Calcular os km totais percorridos por todos os autocarros
    public static int calcularTotaldeKmPercorridosPelaFrotaDeAutocarros(int[][] matrizKm) {

        int soma = 0;

        for (int autocarro = 0; autocarro < matrizKm.length; autocarro++) {

            for (int dia = 0; dia < matrizKm[autocarro].length; dia++) {

                soma += matrizKm[autocarro][dia];

            }

        }

        return soma;

    }
    //e2) Visualizar os km totais percorridos por todos os autocarros
    public static void visualizarTotaldeKmPercorridosPelaFrotaDeAutocarros(int kmTotais) {

        System.out.println("Total de km a percorrer pela frota = " + kmTotais + " km");
        System.out.println();

    }



    //f1) Criar matriz Max Km por dia
    public static int[] matrizkmMaxPorDia(int[][] matrizKm) {

        int[] somas = new int[matrizKm[0].length];

        for (int dias = 0; dias < somas.length; dias++) {

            for (int autocarros = 0; autocarros < matrizKm.length; autocarros++) {

                somas[dias] += matrizKm[autocarros][dias];

            }

        }

        return somas;

    }
    //f2) retornar valor max da matriz anterior
    public static int kmMaxPorDia(int[] somas) {

        int kmMax = 0;

        for (int dias = 0; dias < somas.length; dias++) {

            if(somas[dias] > kmMax){

                kmMax = somas[dias];

            }

        }

        return kmMax;

    }
    //f3) retornar dia com mais km
    public static String diasComMaisKm(int[] somas, int kmMax) {

        StringBuilder diasMax = new StringBuilder();

        for (int dias = 0; dias < somas.length; dias++) {

            if (somas[dias] > kmMax) {

                kmMax = somas[dias];
                // Apaga conteudo do StringBuilder
                diasMax.delete(0,diasMax.length());
                // Acrescenta novo dia detetado
                diasMax.append(String.format(("%d,"),dias));

            }else if (somas[dias] == kmMax){

                // Acrescenta novo dia detetado

                diasMax.append(String.format(("%d,"),dias));

            }

        }

        // Apaga última vírgula da sequência de dias
        diasMax.delete(diasMax.length() - 1, diasMax.length());
        // Devolve conteudo do StringBuilder para o array vazio dado como argumento
        return diasMax.toString();

    }
    //f4) visualizar dia ou dias com mais km e quantos km;
    public static void visualizarDiasComMaisKm(int kmMax, String diaComMaisKm) {

        System.out.println("máximo de kms num dia: (" + kmMax + " km), dias: [" + diaComMaisKm + "]");
        System.out.println();

    }

    //g) Visualizar que autocarros é que permanecem mais de um dia no mesmo sítio
    public static void visualizarAutocarrosQuePermanecemMaisDeUmDiaNoMesmoSitio(int[][] planeamento)    {

        int cont;
        int naoExistem = 0;

        System.out.print("Autocarros que permanecem mais de 1 dia consecutivo na mesma cidade: ");

        for (int autocarros = 0; autocarros < planeamento.length; autocarros++) {

            cont = 0;

            for (int dias = 1; dias < planeamento[autocarros].length; dias++) {

                int cidadeSeguinte = planeamento[autocarros][dias];
                int cidadeAtual = planeamento[autocarros][dias - 1];

                if (cidadeSeguinte == cidadeAtual) {

                    cont++;
                    naoExistem++;

                }

            }

            if (cont > 0) {

                System.out.print("Bus" + autocarros + " ");

            }

        }

        if (naoExistem < 1) {

            System.out.print("Nenhum");

        }

        System.out.println();
        System.out.println();

    }

    // h) visualizar em que dia os autocarros se encontram na mesma cidade
    public static void emQueDiaOsAutocarrosNaMesmaCidade(int[][] planeamento) {

        boolean cidadeEncontrada = false;

        String nomeCidade = "";

        int diaEncontrado = -1;

        for (int dia = planeamento[0].length - 1; !cidadeEncontrada && dia >= 0; dia--) {
            // O primeiro autocarro a ser comparado está sempre na mesma cidade
            int mesmaCidade = 1;

            for (int autocarro = 0; autocarro < planeamento.length - 1; autocarro++) {
                // Se o autocarro seguinte está na mesma cidade, incrementamos o contador
                if (planeamento[autocarro][dia] == planeamento[autocarro + 1][dia]) {
                    mesmaCidade++;
                }
            }
            // Se após percorrer os autocarros todos de um certo dia se verifica a condição, o ciclo acaba
            // O dia e o código da cidade são guardados
            if (mesmaCidade == planeamento.length) {
                nomeCidade = CIDADES[planeamento[0][dia]];
                diaEncontrado = dia;
                cidadeEncontrada = true;
            }
        }

        if (planeamento.length < 2) {

            System.out.println("Só existe um autocarro. Logo, não existem autocarros suficientes para realizar essa comparação");

        } else if (diaEncontrado == -1)

            System.out.println("Os autocarros nunca se encontram no mesmo sítio no mesmo dia");
            // Impressão do dia e cidade encontrados
        else {

            System.out.println("No dia <" + diaEncontrado + ">, todos os autocarros estão em <" + nomeCidade + ">");

        }

        System.out.println();

    }

    //i) Visualizar a percentagem de km efetuada por cada autocarro
    public static double[] calcularPercentagem(int[][] planeamento, int[] kmMaxAutocarro, int kmTotais) {

        double[] percentagem = new double[planeamento.length];

        if (kmTotais != 0) {

            for (int autocarro = 0; autocarro < planeamento.length; autocarro++) {

                percentagem[autocarro] = (double) kmMaxAutocarro[autocarro] * 100 / kmTotais;

            }

        }

        return percentagem;

    }

    public static void visualizarPercentagemDeKm(int[][] planeamento, double[] percentagem, int kmTotais) {

        if (planeamento[0].length < 2) {

            System.out.println("Os autocarros não saiem do ponto de partida");

        } else if (kmTotais == 0) {

            System.out.println("Os autocarros mantiveram-se na mesma cidade durante " + planeamento[0].length + " dias");

        } else {

            for (int autocarro = 0; autocarro < percentagem.length; autocarro++) {

                System.out.print("Bus" + autocarro + "  ");
                System.out.printf("(%.2f%%) :", percentagem[autocarro]);

                for (int dia = 0; dia < (int) percentagem[autocarro] / 10; dia++) {

                    System.out.print("*");

                }

                System.out.println();

            }

        }

        System.out.println();

    }

    //j1) Ler autocarro e dia a partir do ficheiro
    public static int[] lerAutocarroEDia() throws FileNotFoundException {

        Scanner ler = new Scanner(new File(PLANEAMENTO));
        ler.nextLine();

        int linhas = ler.nextInt();
        int colunas = ler.nextInt();

        for (int nrDeLinhas = 0; nrDeLinhas < linhas + 1; nrDeLinhas++) {

            ler.nextLine();

        }

        int nrAutocarro = ler.nextInt();
        int nrDias = ler.nextInt();
        int[] autocarroEDia = new int[]{nrAutocarro, nrDias};

        ler.close();

        return autocarroEDia;

    }
    //j2) Visualizar autocarro(s) mais próximo(s) do(s) autocarro(s) lido(s)
    public static void visualizarAutocarroMaisProximo(int[][] planeamento, int[] autocarroEDia) {

        int autocarroAVerificar = autocarroEDia[0];
        int diaAVerificar = autocarroEDia[1];
        int cidadeAutocarro = planeamento[autocarroAVerificar][diaAVerificar];
        int distanciaMinima = Integer.MAX_VALUE;
        int autocarroMaisProximo = -1;
        int cidadeOutroAutocarroFinal = 0;
        int contadorAutocarros = 1;
        StringBuilder autocarrosProximos = new StringBuilder();
        for (int autocarros = 0; autocarros < planeamento.length; autocarros++) {

            if (autocarros != autocarroAVerificar) {

                int cidadeOutroAutocarro = planeamento[autocarros][diaAVerificar];
                int distancia = DISTANCIAS[cidadeAutocarro][cidadeOutroAutocarro];

                if (distancia < distanciaMinima) {
                    autocarrosProximos.delete(0,autocarrosProximos.length());
                    autocarrosProximos.append(String.format("<Bus%d> e ",autocarros));
                    distanciaMinima = distancia;
                    autocarroMaisProximo = autocarros;
                    cidadeOutroAutocarroFinal = cidadeOutroAutocarro;

                }else if (distancia == distanciaMinima) {
                    autocarrosProximos.append(String.format("<Bus%d> e ", autocarros));
                    contadorAutocarros++;
                }
            }
        }

        System.out.print("No dia <" + diaAVerificar + ">, <Bus" + autocarroAVerificar +  "> estará em " + "<" + CIDADES[cidadeAutocarro] + ">. ");

        if (planeamento.length < 2) {

            System.out.println("Não existem autocarros mais próximos, o <Bus" + autocarroAVerificar + "> é o único autocarro neste planeamento");

        } else if (contadorAutocarros == 1){

            autocarrosProximos.delete(autocarrosProximos.length()-3, autocarrosProximos.length());
            System.out.print(autocarrosProximos + " é o mais próximo. Está em <" + CIDADES[cidadeOutroAutocarroFinal] + "> a <" + distanciaMinima + " km>");

        }else {

            autocarrosProximos.delete(autocarrosProximos.length()-3, autocarrosProximos.length());
            System.out.print(autocarrosProximos + " são os mais próximos. Estão em <" + CIDADES[cidadeOutroAutocarroFinal] + "> a <" + distanciaMinima + " km>");


        }

    }

}