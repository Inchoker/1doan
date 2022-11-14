package com.example.doanweb.service.impl;

import com.example.doanweb.dao.BookingDAO;
import com.example.doanweb.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.doanweb.dao.ThongKeDAO;
import com.example.doanweb.entity.ThongKeDT;
import com.example.doanweb.service.ThongKeService;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class ThongkeServicempl implements ThongKeService {
    @Autowired
    ThongKeDAO thongKeDAO;

    @Autowired
    BookingDAO bookingDAO;

    float result = 0;

    @Override
//    public float getDTByMonth(String monthYear) {
//        if(monthYear.equals("") || monthYear==null){
//            System.out.println("Nếu không chọn tháng thì sẽ lấy tháng hiện tại");
//        }
//        float doanhThu=0;
//
//        ThongKeDT thongKeDT=thongKeDAO.thongKeDT(monthYear);
//        if (checkDT(thongKeDT)){
//            doanhThu=thongKeDAO.doanhThuByMonth(monthYear);
//        }
//        return doanhThu;
//    }

    public boolean checkDT(ThongKeDT dt) {
        return dt != null ? true : false;
    }

    @Override
    public float thongKeLichHenCPM(String monthYear) {
        if (monthYear.equals("") || monthYear == null) {
            System.out.println("Nếu không chọn tháng thì sẽ lấy tháng hiện tại");
        }
        int year = Integer.parseInt(monthYear.substring(0, 4));
        int month = Integer.parseInt(monthYear.substring(5, 7));
        List<Booking> bookingList = bookingDAO.listCPMAppointment(month,year);
        return bookingList.size();
    }

    @Override
    public float thongKeLichHenAll(String monthYear) {
        if (monthYear.equals("") || monthYear == null) {
            System.out.println("Nếu không chọn tháng thì sẽ lấy tháng hiện tại");
        }
        int year = Integer.parseInt(monthYear.substring(0, 4));
        int month = Integer.parseInt(monthYear.substring(5, 7));
        List<Booking> bookingList = bookingDAO.findBookingWithinAMonth(month, year);
        return bookingList.size();
//        return thongKeDAO.thongKeLichHenAll(monthYear);
    }

    @Override
    public float getDTByMonth(String monthYear) {
        if (monthYear.equals("") || monthYear == null) {
            System.out.println("Nếu không chọn tháng thì sẽ lấy tháng hiện tại");
        }
        int year = Integer.parseInt(monthYear.substring(0, 4));
        int month = Integer.parseInt(monthYear.substring(5, 7));
        List<Booking> bookingList = bookingDAO.findBookingWithinAMonth(month, year);
        double sum = bookingList.stream().mapToDouble(Booking::getTotalPrice).sum();
        return (float) sum;
//        return thongKeDAO.thongKeLichHenAll(monthYear);
    }
}
