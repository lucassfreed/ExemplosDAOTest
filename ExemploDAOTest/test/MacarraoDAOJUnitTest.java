
import dao.MacarraoDAO;
import bean.Macarrao;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Alunos
 */
public class MacarraoDAOJUnitTest {

    @Test
    public void testarCadastro() {
        Macarrao nissin = new Macarrao();
        nissin.setMarca("Nissin");
        nissin.setPeso(100);
        nissin.setAlDente(false);
        nissin.setTipo("Na Hora");

        int identificador = new MacarraoDAO().cadastrar(nissin);

        assertNotSame(identificador, -1);
        Macarrao macarraoBuscado = new MacarraoDAO().obterPeloId(identificador);
        assertNotNull(macarraoBuscado);

        /* Verifica se o que foi guardado em um objeto java foi o que realmente
        foi armazenado no banco, ou seja consistir se as informações são iguais.*/
        assertEquals(nissin.getMarca(), macarraoBuscado.getMarca());
        assertEquals(nissin.getPeso(), macarraoBuscado.getPeso(), 0);
        assertEquals(nissin.isAlDente(), macarraoBuscado.isAlDente());
        assertEquals(nissin.getTipo(), macarraoBuscado.getTipo());
    }

    @Test
    public void testarEdicao() {
        Macarrao magali = new Macarrao();
        magali.setMarca("Magali");
        magali.setPeso(100);
        magali.setAlDente(true);
        magali.setTipo("Da Hora");

        int identificador = new MacarraoDAO().cadastrar(magali);
        magali.setId(identificador);
        
        magali.setMarca("Monica");
        magali.setPeso(8001);
        magali.setAlDente(false);
        magali.setTipo("Na Hora");
        boolean alterado = new MacarraoDAO().alterar(magali);
        assertTrue(alterado);
        
        Macarrao macarraoBuscado = new MacarraoDAO().obterPeloId(identificador);
        assertNotNull(macarraoBuscado);
        assertEquals(magali.getMarca(), macarraoBuscado.getMarca());
        assertEquals(magali.getPeso(), macarraoBuscado.getPeso(), 0);
        assertEquals(magali.isAlDente(), macarraoBuscado.isAlDente());
        assertEquals(magali.getTipo(), macarraoBuscado.getTipo());
    }
    
    @Test
    public void testarApagar() {
        Macarrao magali = new Macarrao();
        magali.setMarca("Magali");
        magali.setPeso(100);
        magali.setAlDente(true);
        magali.setTipo("Da Hora");

        int identificador = new MacarraoDAO().cadastrar(magali);
        boolean excluido = new MacarraoDAO().apagar(identificador);
        assertTrue(excluido);
        Macarrao macarraoBuscado = new MacarraoDAO().obterPeloId(identificador);
        assertNull(macarraoBuscado);
    }
    
}
