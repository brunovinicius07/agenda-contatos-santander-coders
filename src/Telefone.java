import java.util.Objects;

public class Telefone {

    private static long ultimoId = 0;

    private Long id;
    private Long ddd;
    private Long numero;

    public Telefone() {
    }

    public Telefone( Long ddd, Long numero) {
        this.id = gerarNovoId();
        this.ddd = ddd;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDdd() {
        return ddd;
    }

    public void setDdd(Long ddd) {
        this.ddd = ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(numero, telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    public static synchronized long gerarNovoId() {
        return ++ultimoId;
    }
}
