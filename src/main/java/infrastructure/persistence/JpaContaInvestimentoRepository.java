package infrastructure.persistence;

import java.util.Optional;

import application.repository.ContaInvestimentoRepository;
import domain.conta.Conta;
import domain.conta.ContaInvestimento;
import infrastructure.entity.ContaInvestimentoEntity;
import infrastructure.mapper.ContaInvestimentoMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class JpaContaInvestimentoRepository implements ContaInvestimentoRepository{
	
	private EntityManagerFactory emf;
	
	public JpaContaInvestimentoRepository(EntityManagerFactory emf) {
		this.emf = emf;
		}
	

	@Override
	public ContaInvestimento salvar (Conta contaInvestimentoDomain) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try{
			tx.begin();
			
			ContaInvestimentoEntity contaInvestimentoEntity = ContaInvestimentoMapper.toEntity(contaInvestimentoDomain);
			
			if(contaInvestimentoEntity.getId() == null) {
				em.persist(contaInvestimentoEntity);
			}else {
				em.merge(contaInvestimentoEntity);
			}
			
			tx.commit();
			
			ContaInvestimento contaInvestimentoDomai = ContaInvestimentoMapper.toDomain(contaInvestimentoEntity);
			
			return contaInvestimentoDomai;
			
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
	public Optional<Conta> BuscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();

		try {
			ContaInvestimentoEntity contaInvestimentoEntity = em.find(ContaInvestimentoEntity.class, id);
			
			if(contaInvestimentoEntity == null) {
				return Optional.empty();
			}
			
			ContaInvestimento contaInvestimentoDomain = ContaInvestimentoMapper.toDomain(contaInvestimentoEntity);
			
			return Optional.of(contaInvestimentoDomain);
			
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
			ContaInvestimentoEntity contaInvestimentoEntity = em.find(ContaInvestimentoEntity.class, id);
			
			if(contaInvestimentoEntity != null) {
				em.remove(contaInvestimentoEntity);
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
}
