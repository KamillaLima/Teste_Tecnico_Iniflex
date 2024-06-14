import br.com.iniflex.Funcionario;

import javax.annotation.processing.SupportedSourceVersion;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final DateTimeFormatter FORMATADOR_DE_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormatSymbols FORMATADOR_MONETARIO = new DecimalFormatSymbols(new Locale("pt", "BR"));

    public static void main(String[] args) {

        //Criando a lista de funcionários e acrescentando novos funcionários na mesma
        List<Funcionario> listaDeFuncionarios = criarListaDeFuncionarios();

        //Removendo o funcionário João
        listaDeFuncionarios.removeIf(funcionario -> funcionario.getNome().equals("Joao"));

        //Imprimindo a lista de funcionários com suas respectivas informações
        imprimirListaDeFuncionarios(listaDeFuncionarios);

        //Aumentando o salário em 10% de todos os funcionários
        atualizarSalarios(listaDeFuncionarios);

        //Imprimindo a lista de funcionários com o salário atualizado
        //imprimirListaDeFuncionarios(listaDeFuncionarios);

        //Criando map e agrupando os funcionários pela sua função
        Map<String ,List<Funcionario>> ListaFuncionariosPorFuncao = new HashMap<>();
        ListaFuncionariosPorFuncao = agruparPorFuncao(listaDeFuncionarios);

        //Imprimir funcionários de acordo com a sua função
        imprimirAgrupadoPorFuncao(ListaFuncionariosPorFuncao);

        //Imprimindo funcionários que fazem aniversário no mês de outubro ou no mês de dezembro
        funcionariosQueFazemAniversariosEmOutubroeDezembro(listaDeFuncionarios);

        //Imprimindo o funcionário mais velho da empresa
        imprimirFuncionarioMaisVelho(listaDeFuncionarios);

        //Imprimir funcionários em ordem alfabética
        imprimirEmOrdemAlfabetica(listaDeFuncionarios);

        //Imprimindo o total de todos os salários informados
        imprimirSalarioTotalDosSalarios(listaDeFuncionarios);

        //Imprimindo quantos salários mínimos cada funcionário recebe
        totalDeSalarioMinimoPorFuncionario(listaDeFuncionarios);

    }


    public static List<Funcionario> criarListaDeFuncionarios(){
        List<Funcionario> listaDeFuncionarios = new ArrayList<>();
        listaDeFuncionarios.add(new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal("2009.44"), "Operador"));
        listaDeFuncionarios.add(new Funcionario("Joao", LocalDate.of(1990, Month.MAY, 12), new BigDecimal("2284.38"), "Operador"));
        listaDeFuncionarios.add(new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), new BigDecimal("9836.14"), "Coordenador"));
        listaDeFuncionarios.add(new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal("19119.88"), "Diretor"));
        listaDeFuncionarios.add(new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 5), new BigDecimal("2234.68"), "Recepcionista"));
        listaDeFuncionarios.add(new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal("1582.72"), "Operador"));
        listaDeFuncionarios.add(new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31), new BigDecimal("4071.84"), "Contador"));
        listaDeFuncionarios.add(new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8), new BigDecimal("3017.45"), "Gerente"));
        listaDeFuncionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, Month.MAY, 24), new BigDecimal("1606.85"), "Eletricista"));
        listaDeFuncionarios.add(new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2), new BigDecimal("2799.93"), "Gerente"));
        return listaDeFuncionarios;
    }
    public static void imprimirListaDeFuncionarios(List<Funcionario> listaFuncionarios){
        System.out.println("FUNCIONÁRIOS DA EMPRESA : ");
        for(Funcionario func : listaFuncionarios){
            FORMATADOR_MONETARIO.setDecimalSeparator(',');
            FORMATADOR_MONETARIO.setGroupingSeparator('.');
            System.out.println("Nome do funcionário : " + func.getNome());
            System.out.println("Função do funcionário : " + func.getFuncao());
            System.out.println("Data de nascimento do funcionário : "+func.getDataNascimento().format(FORMATADOR_DE_DATA));
            System.out.println("Salário do funcionário : "+ new DecimalFormat("##,###.##" , FORMATADOR_MONETARIO).format(func.getSalario())+ "\n");
        }
        System.out.println("\n\n\n");
    }
    public static void atualizarSalarios(List<Funcionario> listaFuncionarios){
        for(Funcionario funcionario : listaFuncionarios){
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(new BigDecimal("0.10"));
            BigDecimal salarioComAumento = salarioAtual.add(aumento);
            funcionario.setSalario(salarioComAumento);
        }
    }
    public static  Map<String , List<Funcionario>> agruparPorFuncao(List<Funcionario> listaFuncionarios){
        Map<String , List<Funcionario> > mapListaFuncionarios = new HashMap<>();
        for(Funcionario func : listaFuncionarios){
          String funcaoFuncionario = func.getFuncao();
            mapListaFuncionarios.putIfAbsent(funcaoFuncionario,new ArrayList<>());
            mapListaFuncionarios.get(funcaoFuncionario).add(func);
        }

        return mapListaFuncionarios;
    }
    public static  void imprimirAgrupadoPorFuncao(Map<String , List<Funcionario>> listaFuncionarios){
        for (Map.Entry<String, List<Funcionario>> entry : listaFuncionarios.entrySet()) {
            String funcao = entry.getKey();
            List<Funcionario> funcionarios = entry.getValue();
            System.out.println("FUNCIONÁRIOS DA EMPRESA POR FUNÇÃO : ");
            System.out.println("Função : " + funcao);
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario.getNome() );
            }
            System.out.println();
        }
        System.out.println("\n\n\n");
    }
    public static void funcionariosQueFazemAniversariosEmOutubroeDezembro(List<Funcionario> listaFuncionarios){
        System.out.println("FUNCIONÁRIOS QUE NASCERAM EM OUTUBRO OU DEZEMBRO : ");
        for(Funcionario funcionario : listaFuncionarios){
            if(funcionario.getDataNascimento().getMonth().equals(Month.OCTOBER) || funcionario.getDataNascimento().getMonth().equals(Month.DECEMBER)) {
                System.out.println("Nome do funcionário :" + funcionario.getNome() + " -- " + "Mês de aniversário : " + funcionario.getDataNascimento().getMonth());
            }
        }
        System.out.println("\n\n\n");
    }
    public static int caluloIdades(LocalDate dataNascimento){
        LocalDate dataHoje = LocalDate.now();
        Period periodo  = Period.between(dataNascimento,dataHoje);
        return periodo.getYears();
    }
    public static void imprimirFuncionarioMaisVelho(List<Funcionario> listaFuncionarios){
        int idadeMaisVelha =0;
        String nomeFuncionario="";
        for(Funcionario funcionario : listaFuncionarios){
            int idadeFuncionario = caluloIdades(funcionario.getDataNascimento());
            if(idadeMaisVelha < idadeFuncionario){
                idadeMaisVelha = idadeFuncionario;
                nomeFuncionario = funcionario.getNome();
            }
        }
        System.out.println("O(A) funcionário(a) com a maior idade é o(a) " + nomeFuncionario + " com " + idadeMaisVelha + " anos ");
        System.out.println("\n\n\n");
    }
    public static void imprimirEmOrdemAlfabetica(List<Funcionario> listaFuncionarios){

        Collections.sort(listaFuncionarios,(f1,f2) -> f1.getNome().compareTo(f2.getNome()));
        System.out.println("NOMES EM ORDEM ALFABETICA : ");
        for(Funcionario funcionario : listaFuncionarios){
            System.out.println(funcionario.getNome());
        }
        System.out.println("\n\n\n");

    }
    public static void imprimirSalarioTotalDosSalarios(List<Funcionario> listaFuncionarios){
        BigDecimal totalSalarios =  BigDecimal.ZERO;
       for(Funcionario funcionario : listaFuncionarios){
           totalSalarios = totalSalarios.add(funcionario.getSalario());
       }

        System.out.println("O total de salário dos funcionários listados é de R$"+ new DecimalFormat("##,###.##", FORMATADOR_MONETARIO).format(totalSalarios) );
        System.out.println("\n\n\n");
    }
    public static void totalDeSalarioMinimoPorFuncionario(List<Funcionario> listaFuncionarios){
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario funcionario : listaFuncionarios) {
            BigDecimal salarioFuncionario = funcionario.getSalario();
            int totalSalariosMinimos = salarioFuncionario.divideToIntegralValue(salarioMinimo).intValue();
            System.out.println("O(A) funcionário(a) " + funcionario.getNome() + " ganha aproximadamente " + totalSalariosMinimos + " salário(s) mínimo(s)");
        }
        System.out.println("\n\n\n");
    }



}