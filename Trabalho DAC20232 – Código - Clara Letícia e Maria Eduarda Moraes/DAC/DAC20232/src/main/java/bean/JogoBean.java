package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.JogoDAO;
import entidades.Jogo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class JogoBean {
 
    private Jogo jogo = new Jogo();
    private List<Jogo> jogos = new ArrayList<Jogo>();
    private Jogo jogadaEditavel = new Jogo();

    public Jogo getJogo() {
        return jogo;
    }

    public JogoBean() {
        jogadaEditavel = new Jogo();
    }

    public List<Jogo> getJogos() {
        try {
            JogoDAO jogoDAO = new JogoDAO(); // Criando uma instância de JogoDAO
            jogos = jogoDAO.listar(); // Chamando o método salvar através da instância de JogoDAO
            
            // Calculando o resultado para cada jogo na lista
            for (Jogo jogo : jogos) {
                String resultado = jogo.calcularResultado(); //calcular resultado
                jogo.setResultado(resultado);
            }

            return jogos;
        } catch (Exception e) {
            e.printStackTrace();
            return jogos;
        }
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public void salvarJogo() {
        if (isTimesIguais()) {
            exibirMensagemErro("Os times não podem ser iguais.");
        } else {
            preencherDataCadastro(); // Preenche a data de cadastro automaticamente
            boolean salvouComSucesso = salvarNoBanco(); // Tenta salvar o jogo no banco de dados

            if (salvouComSucesso) {
                exibirMensagemSucesso("Jogo salvo com sucesso.");
            } else {
                exibirMensagemErro("Erro ao salvar o jogo. Tente novamente.");
            }
        }
    }

    private boolean isTimesIguais() {
        return jogo.getTime1().equals(jogo.getTime2());
    }

    private void preencherDataCadastro() {
        jogo.setDataCadastro(new Date()); // Define a data de cadastro como a data/hora atuais
    }
 
    private boolean salvarNoBanco() {
        try {
            JogoDAO jogoDAO = new JogoDAO(); // Criando uma instância de JogoDAO
            jogoDAO.salvar(jogo); // Chamando o método salvar através da instância de JogoDAO
            return true;
        } catch (Exception e) {
            // Tratamento de exceção aqui
            e.printStackTrace();
            return false;
        }
    }    
   
    public void carregarJogadaParaEdicao(int id) {
        setJogadaEditavel(JogoDAO.acharPorId(id));
    }

  
    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public void excluirJogada(int id) {
        Jogo jogadaParaExcluir = JogoDAO.acharPorId(id);
        if (jogadaParaExcluir != null) {
            JogoDAO.excluir(jogadaParaExcluir);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogada Excluída com Sucesso!"));
        }
    }

    private void exibirMensagemErro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", mensagem));
    }

    private void exibirMensagemSucesso(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", mensagem));
    }

    public Jogo getJogadaEditavel() {
        return jogadaEditavel;
    }

    public void setJogadaEditavel(Jogo jogadaEditavel) {
        this.jogadaEditavel = jogadaEditavel;
    }
    public Map<String, Integer> calcularEstatisticas() {
        Map<String, Integer> estatisticas = new HashMap<>();
        
        int A = 0;
        int B = 0;
        int C = 0;
        
        for (Jogo j : jogos) {
            if ("A".equals(j.getTime1())) {
                A++;
            } else if ("B".equals(j.getTime1())) {
                B++;
            } else if ("C".equals(j.getTime1())) {
                C++;
            }         
            if ("A".equals(j.getTime2())) {
                A++;
            } else if ("B".equals(j.getTime2())) {
                B++;
            } else if ("C".equals(j.getTime2())) {
                C++;
            }
        }        
        estatisticas.put("A", A);
        estatisticas.put("B", B);
        estatisticas.put("C", C);
        
        return estatisticas;
    }
    
}