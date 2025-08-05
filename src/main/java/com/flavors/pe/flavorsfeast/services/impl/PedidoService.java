package com.flavors.pe.flavorsfeast.services.impl;

import com.flavors.pe.flavorsfeast.dto.DetallePedidoDto;
import com.flavors.pe.flavorsfeast.dto.ListarPedidoDto;
import com.flavors.pe.flavorsfeast.dto.RegistarPedidoDto;
import com.flavors.pe.flavorsfeast.models.*;
import com.flavors.pe.flavorsfeast.repositories.PedidoRepository;
import com.flavors.pe.flavorsfeast.repositories.ProductoRepository;
import com.flavors.pe.flavorsfeast.repositories.UsuarioRepository;
import com.flavors.pe.flavorsfeast.services.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void registrarPedido(RegistarPedidoDto registarPedidoDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow();

        Pedido pedido = Pedido
                .builder()
                .fechaPedido(LocalDate.now())
                .direccion(registarPedidoDto.getDireccion())
                .sucursal(registarPedidoDto.getSucursal())
                .estado(Estado.pendiente)
                .usuario(usuario)
                .tipoEntrega(registarPedidoDto.getTipoEntrega())
                .subtotal(registarPedidoDto.getSubtotal())
                .build();


        List<DetallePedido> detallePedidos = registarPedidoDto
                .getDetallePedido()
                .stream().map(detalleDto -> {
                    Producto producto = productoRepository.findById(detalleDto.getIdProducto())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));


                    DetallePedido detalle = new DetallePedido();
                    detalle.setProducto(producto);
                    detalle.setCantidad(detalleDto.getCantidad());
                    detalle.setPrecioUnitario(producto.getPrecio());
                    detalle.setPedido(pedido);
                    return detalle;
                }).toList();

        pedido.setDetallePedidos(detallePedidos);

        pedidoRepository.save(pedido);

    }

    @Override
    public List<ListarPedidoDto> listarPedidos() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();

        List<Pedido> pedidos = pedidoRepository.findAllByUsuarioCorreoConDetallesYProductos(correo);

        return pedidos.stream().map(p -> {
            ListarPedidoDto dto = new ListarPedidoDto();
            dto.setIdPedido(p.getIdPedido());
            dto.setFechaPedido(p.getFechaPedido());
            dto.setDireccion(p.getDireccion());
            dto.setEstado(p.getEstado().name());
            dto.setSucursal(p.getSucursal());
            dto.setTipoEntrega(p.getTipoEntrega());
            dto.setSubtotal(p.getSubtotal());

            List<DetallePedidoDto> detallesDto = p.getDetallePedidos().stream().map(detalle -> {
                DetallePedidoDto det = new DetallePedidoDto();
                det.setNombre(detalle.getProducto().getNombre());
                det.setCantidad(detalle.getCantidad());
                det.setPrecioUnitario(detalle.getPrecioUnitario());
                det.setDescripcion(detalle.getProducto().getDescripcion());
                det.setImagenUrl(detalle.getProducto().getImagenUrl());
                return det;
            }).toList();

            dto.setDetallePedido(detallesDto);
            return dto;
        }).toList();
    }

}
