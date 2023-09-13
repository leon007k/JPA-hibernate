package br.com.alura.tienda.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha = LocalDate.now();
    private BigDecimal valorTotal;
    @ManyToOne
    private Cliente cliente; // * Relacion de cliente con Pedidos. Un cliente puede tener muchos pedidos

//    @ManyToOne // * Se crea relacion de pedidos con productos, creando una tabla nueva con su respectivo nombre
//    @JoinTable(name = "items_pedido")
//    private List<Producto> produtos;
    // * Al tener un OneToMany aqui, y en la clase ItemsPedido tenemos ManyToOne, creamos un ManyToMany
    // * El mappedBy nos permitira conectar esta lista, con la entidad ItemsPedido
    @OneToMany(mappedBy = "pedido")
    private List<ItemsPedido> items = new ArrayList<>();

    public Pedido(){}

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarItems(ItemsPedido item) {
        item.setPedido(this);
        this.items.add(item);
    }
    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
