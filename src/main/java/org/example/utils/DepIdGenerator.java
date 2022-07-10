package org.example.utils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicReference;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class DepIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
        throws HibernateException {
        String prefix = "DEP";
        AtomicReference<String> generatedId = new AtomicReference<>("");

        session.doWork(connection -> {
            try {

                Statement statement=connection.createStatement();

                ResultSet rs=statement.executeQuery("select max(Id) as Id from dbo.DEPARTMENTS");

                if(rs.next())
                {
                    String idString=rs.getString(1);
                    int id;
                    if (idString == null) {
                        id = 1;
                    } else {
                        id = Integer.parseInt(idString.substring(prefix.length())) + 1;
                    }

                    generatedId.set(String.format(prefix+"%02d", id));

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        return generatedId.get();
    }
}
