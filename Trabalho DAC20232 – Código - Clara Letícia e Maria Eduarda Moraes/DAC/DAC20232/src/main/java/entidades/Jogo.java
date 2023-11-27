package entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPartida;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column
    private String time1;

    @Column
    private String time2;
    
    @Column(name = "resultado")
    private String resultado;

    public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Column
    private Integer golsTime1; 

    @Column
    private Integer golsTime2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public Integer getGolsTime1() {
        return golsTime1;
    }

    public void setGolsTime1(Integer golsTime1) {
        this.golsTime1 = golsTime1;
    }

    public Integer getGolsTime2() {
        return golsTime2;
    }

    public void setGolsTime2(Integer golsTime2) {
        this.golsTime2 = golsTime2;
    }
   
    
    public String calcularResultado() {
        if (golsTime1 > golsTime2) {
            return "TIME 1 GANHOU";
        } else if (golsTime2 > golsTime1) {
            return "TIME 2 GANHOU";
        } else {
            return "EMPATE";
        }
    }
}