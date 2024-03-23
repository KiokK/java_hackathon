package by.kiok.tgbot.repository;

import by.kiok.tgbot.model.Currency;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class CurrencyRepository {

    private final SessionFactory sessionFactory;

    public Optional<Currency> create(Currency currency) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(currency);

        return Optional.ofNullable(currency);
    }

    public void create(List<Currency> listCurrency) {
        Session session = sessionFactory.getCurrentSession();
        for (Currency currency : listCurrency) {
            session.persist(currency);
        }

        return;
    }

    public Optional<Currency> updateAndGetOld(Currency currency) {
        Session session = sessionFactory.getCurrentSession();
        Currency findCurrency = session.createQuery("SELECT c FROM Currency c WHERE c.symbol = ?1", Currency.class)
                .setParameter(1, currency.getSymbol()).getSingleResultOrNull();
        if (findCurrency == null) {

            return Optional.empty();
        }

        return Optional.of(session.merge(currency));
    }
}
