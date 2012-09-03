package info.kkrzyzak.kk.model;

import javax.persistence.*;

@Entity
public class ChatUser implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "CODE", unique = true, nullable = false, length = 10)
    private String code;

    @Column(name = "NAME", unique = true, nullable = false, length = 20)
    private String name;

    public ChatUser() {
    }

    public ChatUser(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChatUser [stockCode=" + code + ", stockId=" + id
                + ", stockName=" + name + "]";
    }



}