package chapter5.dao.impl;

import chapter4.model.Account;
import chapter5.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class XmlAccountDaoImpl implements AccountDao {

    private final JdbcTemplate template;

    @Autowired
    public XmlAccountDaoImpl(JdbcTemplate jdbcTemplate){
        this.template = jdbcTemplate;
    }

    @Override
    public int addAccount(Account account) {
        return template.update(
                "INSERT INTO account(id, name, balance) VALUES (?,?,?)",
                account.getId(), account.getName(), account.getBalance());
    }

    @Override
    public int updateAccount(Account account) {
        return template.update("UPDATE account SET name=?, balance=? WHERE id=?",
                account.getName(), account.getBalance(), account.getId());
    }

    @Override
    public int deleteAccount(Account account) {
        return template.update("DELETE FROM account WHERE id=?", account.getId());
    }

    @Override
    public Account findOne(long id) {
        return template.queryForObject("SELECT * FROM account WHERE id=?", new BeanPropertyRowMapper<>(Account.class), id);
    }

    @Override
    public List<Account> findAll() {
        return template.query("SELECT * FROM account", new BeanPropertyRowMapper<>(Account.class));
    }

    /**
     * @param isErr 是否模拟出错
     */
    @Override
    public void transfer(long formId, long toId, double money, boolean isErr) {
        template.update("UPDATE account SET balance=balance+? WHERE id=?", money, formId);
        //noinspection NumericOverflow,divzero
        System.out.println(isErr?1/0:1);
        template.update("UPDATE account SET balance=balance-? WHERE id=?", money, toId);
    }
}
