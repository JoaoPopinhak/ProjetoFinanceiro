package infrastructure.persistence;

import java.util.Optional;

import application.repository.MovimentacaoRepository;
import domain.movimentacao.Movimentacao;
import infrastructure.entity.MovimentacaoEntity;
import infrastructure.mapper.MovimentacaoMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class JpaMovimentacaoRepository implements MovimentacaoRepository{
	
	private EntityManagerFactory emf;
	
	public JpaMovimentacaoRepository(EntityManagerFactory emf){
		this.emf = emf;
	}

	@Override
	public Movimentacao salvar(Movimentacao movimentacao) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
		
			MovimentacaoEntity movimentacaoEntity = MovimentacaoMapper.toEntity(movimentacao, em);
			
			if(movimentacaoEntity.getId() == null) {
				em.persist(movimentacaoEntity);
			}else {
				em.merge(movimentacaoEntity);
			}
			
			tx.commit();
			
			Movimentacao movimentacaoDomain = MovimentacaoMapper.toDomain(movimentacaoEntity);
			
			return movimentacaoDomain;
				
		}catch(Exception e) {
			if(tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw e;
		}finally {
			em.close();
		}
	}

	@Override
	public Optional<Movimentacao> buscarPorId(Long id) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			MovimentacaoEntity movimentacaoEntity = em.find(MovimentacaoEntity.class, id);
			
			if(movimentacaoEntity == null) {
				return Optional.empty();
			}
			
			Movimentacao movimentacaoDomain = MovimentacaoMapper.toDomain(movimentacaoEntity);
			
			return Optional.of(movimentacaoDomain);
			
		}finally {
			em.close();
		}
	}

	@Override
	public void deletar(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			MovimentacaoEntity movimentacaoEntity = em.find(MovimentacaoEntity.class, id);
			
			if(movimentacaoEntity != null) {
				em.remove(movimentacaoEntity);	
			}
			
			tx.commit();
		}catch(Exception e) {
			if(tx.isActive()) {
				tx.rollback();
			}
		}finally {
			em.close();
		}
			
	}

	@Override
	public void deletarPorConta(Long idConta) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			em.createQuery("DELETE FROM MovimentacaoEntity m WHERE m.contaMovimentacao.id = :id").setParameter("id", idConta).executeUpdate();     
			
			tx.commit();
		}catch(Exception e) {
			if(tx.isActive()) {
				tx.rollback();
			}
		}finally {
			em.close();
		}
	}
}
