package online.pubudu.springstarter.integration.database.entity;

import javax.persistence.*;

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
