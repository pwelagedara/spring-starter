package online.pubudu.springstarter.integration.database.entity;

import javax.persistence.*;

@Entity
public class Manager {

    @Id
    private Long id;

    @Column(nullable = false)
    private String city;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Employee employee;

    public Manager() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}