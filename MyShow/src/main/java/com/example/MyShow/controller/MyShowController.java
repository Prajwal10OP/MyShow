package com.example.MyShow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyShow.model.BookRequest;
import com.example.MyShow.repository.MyShowRepository;

@RestController
@RequestMapping("BookMyShow/Service")
public class MyShowController {

	@Autowired
	private MyShowRepository repository;

	@PostMapping("/bookingShow")
	public String bookShow(@RequestBody BookRequest bookRequest) {
		BookRequest response = repository.save(bookRequest);
		return "Hi " + response.getUserName() + " your Request for " + response.getShowName() + " on date "
				+ response.getBookingTime() + " Booked successfully..";

	}

	@GetMapping("/getAllBooking")
	public List<BookRequest> getAllBooking() {
		return repository.findAll();
	}

	@GetMapping("/getBooking/{bookingId}")
	public BookRequest getBooking(@PathVariable int bookingId) {
		return repository.findById(bookingId).get();
	}

	@DeleteMapping("/cancelBooking/{bookingId}")
	public String cancelBooking(@PathVariable int bookingId) {
		repository.deleteById(bookingId);
		return "Booking cancelled with bookingId : " + bookingId;
	}

	@PutMapping("/updateBooking/{bookingId}")
	public BookRequest updateBooking(@RequestBody BookRequest updateBookRequest, @PathVariable int bookingId) {
		BookRequest dbResponse = repository.findById(bookingId).get();
		dbResponse.setBookingTime(updateBookRequest.getBookingTime());
		dbResponse.setPrice(updateBookRequest.getPrice());
		dbResponse.setShowName(updateBookRequest.getShowName());
		dbResponse.setUserCount(updateBookRequest.getUserCount());
		repository.saveAndFlush(dbResponse);
		return dbResponse;
	}
}
