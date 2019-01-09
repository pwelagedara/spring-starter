package online.pubudu.springstarter.integration.database.entity;

import javax.persistence.*;

/**
 * <p>
 *      Database Entity Employee. For H2 and PostgreSQL GenerationType.SEQUENCE is used. For MySQL GenerationType.IDENTITY is used.
 * </p>
 * <p>
 *     This configuration is done a using mysql-orm.xml file which overrides the Entity Configuration here.
 * </p>
 * <p>
 *     spring.jpa.mapping-resources is set in application-mysql.yaml to get this to work. If you have mysql-orm.xml in META-INF the configuration will always be overridden. Therefore have it somewhere else( like resources/db/orm/).
 * </p>
 * <p>
 *     Refer to <a href="https://thoughts-on-java.org/hibernate-tips-override-primary-key-generation-strategy/">Overriding Primary Key Generation Strategy</a> and
 *     <a href="https://stackoverflow.com/questions/32062828/spring-boot-load-orm-xml">Loading ORM XML</a> for more information.
 * </p>
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,  generator = "seq_employee_id")
    @SequenceGenerator(name="seq_employee_id",sequenceName="seq_employee_id", allocationSize=5)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "employee")
    private Manager manager;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
