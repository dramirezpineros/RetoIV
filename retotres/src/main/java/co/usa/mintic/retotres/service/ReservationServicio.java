package co.usa.mintic.retotres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.mintic.retotres.model.Reservation;
import co.usa.mintic.retotres.repository.ReservationRepositorio;



@Service
public class ReservationServicio {
    @Autowired
    private ReservationRepositorio reservationRepositorio;

    public List<Reservation> getAll(){
        return reservationRepositorio.getAll();
    }

    public Optional<Reservation> getReserva(int id){
        return reservationRepositorio.getReserva(id);
    }
    
    
    public Reservation save(Reservation reserva){
        if(reserva.getIdReservation()==null){
            return reservationRepositorio.save(reserva);
        }else{
            Optional<Reservation> consulta=reservationRepositorio.getReserva(reserva.getIdReservation());
            if(consulta.isEmpty()){
                return reservationRepositorio.save(reserva);
            }else{
                return reserva;
            }
        }
    }

    public boolean deleteReserva(int id){
        Optional<Reservation> consulta=reservationRepositorio.getReserva(id);
        if(!consulta.isEmpty()){
            reservationRepositorio.delete(consulta.get());
            return true;
        }
        return false;
    }

    public Reservation update(Reservation reserva){
        if(reserva.getIdReservation()!=null){
            Optional<Reservation> consulta=reservationRepositorio.getReserva(reserva.getIdReservation());
            if(!consulta.isEmpty()){
                if(reserva.getStartDate()!=null){
                    consulta.get().setStartDate(reserva.getStartDate());
                }
            if(reserva.getDevolutionDate()!=null){
                consulta.get().setDevolutionDate(reserva.getDevolutionDate());
                }
            if(reserva.getStatus ()!=null){
                consulta.get().setStatus(reserva.getStatus());
                }
            if(reserva.getScore()!=null){
                consulta.get().setScore(reserva.getScore());
            }

            return reservationRepositorio.save(consulta.get());
            }
        }
        return reserva;
    }
    
}
