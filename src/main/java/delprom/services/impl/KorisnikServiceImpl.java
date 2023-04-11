package delprom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import delprom.dtos.KorisnikDto;
import delprom.dtos.KorisnikResponse;
import delprom.entities.Korisnik;
import delprom.exceptions.ResourceNotFoundException;
import delprom.repositories.KorisnikRepository;
import delprom.services.KorisnikService;

@Service
public class KorisnikServiceImpl implements KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public KorisnikServiceImpl(KorisnikRepository korisnikRepository, PasswordEncoder passwordEncoder) {
		this.korisnikRepository = korisnikRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public KorisnikDto createKorisnik(KorisnikDto korisnikDto) {

		// convert DTO to entity
		Korisnik korisnik = mapToEntity(korisnikDto);
		Korisnik newKorisnik = korisnikRepository.save(korisnik);

		// convert entity to DTO
		KorisnikDto korisnikResponse = mapToDTO(newKorisnik);
		return korisnikResponse;
	}

	@Override
	public KorisnikResponse getAllKorisnik(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Korisnik> korisniks = korisnikRepository.findAll(pageable);

		// get content for page object
		List<Korisnik> listOfKorisnik = korisniks.getContent();
		List<KorisnikDto> content = listOfKorisnik.stream().map(korisnik -> mapToDTO(korisnik))
				.collect(Collectors.toList());

		KorisnikResponse korisnikResponse = new KorisnikResponse();
		korisnikResponse.setContent(content);
		korisnikResponse.setPageNo(korisniks.getNumber());
		korisnikResponse.setPageSize(korisniks.getSize());
		korisnikResponse.setTotalElements(korisniks.getTotalElements());
		korisnikResponse.setTotalPages(korisniks.getTotalPages());
		korisnikResponse.setLast(korisniks.isLast());

		return korisnikResponse;
	}

	@Override
	public KorisnikDto getKorisnikById(Integer id) {
		Korisnik korisnik = korisnikRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return mapToDTO(korisnik);
	}

	@Override
	public KorisnikDto updateKorisnik(KorisnikDto korisnikDto, Integer id) {
		Korisnik korisnik = korisnikRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		korisnik.setIme(korisnikDto.getIme());
		korisnik.setPrezime(korisnikDto.getPrezime());
		korisnik.setDatumRodjenja(korisnikDto.getDatumRodjenja());
		korisnik.setAdresa(korisnikDto.getAdresa());
		korisnik.setTelefon(korisnikDto.getTelefon());
		korisnik.setEmail(korisnikDto.getEmail());
		korisnik.setUsername(korisnikDto.getUsername());
		korisnik.setPassword(passwordEncoder.encode(korisnikDto.getPassword()));
		korisnik.setUloga(korisnikDto.getUloga());

		Korisnik updatedKorisnik = korisnikRepository.save(korisnik);
		return mapToDTO(updatedKorisnik);
	}

	@Override
	public void deleteKorisnikById(Integer id) {
		Korisnik korisnik = korisnikRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		korisnikRepository.delete(korisnik);
	}

	// convert DTO to entity
	private Korisnik mapToEntity(KorisnikDto korisnikDto) {
		Korisnik korisnik = new Korisnik();
		korisnik.setIme(korisnikDto.getIme());
		korisnik.setPrezime(korisnikDto.getPrezime());
		korisnik.setDatumRodjenja(korisnikDto.getDatumRodjenja());
		korisnik.setAdresa(korisnikDto.getAdresa());
		korisnik.setTelefon(korisnikDto.getTelefon());
		korisnik.setEmail(korisnikDto.getEmail());
		korisnik.setUsername(korisnikDto.getUsername());
		korisnik.setPassword(passwordEncoder.encode(korisnikDto.getPassword()));
		korisnik.setUloga("ROLE_KUPAC");
		return korisnik;
	}

	// convert Entity into DTO
	private KorisnikDto mapToDTO(Korisnik korisnik) {
		KorisnikDto korisnikDto = new KorisnikDto();
		korisnikDto.setKorisnikId(korisnik.getKorisnikId());
		korisnikDto.setIme(korisnik.getIme());
		korisnikDto.setPrezime(korisnik.getPrezime());
		korisnikDto.setDatumRodjenja(korisnik.getDatumRodjenja());
		korisnikDto.setAdresa(korisnik.getAdresa());
		korisnikDto.setTelefon(korisnik.getTelefon());
		korisnikDto.setEmail(korisnik.getEmail());
		korisnikDto.setUsername(korisnik.getUsername());
		korisnikDto.setPassword(korisnik.getPassword());
		korisnikDto.setUloga(korisnik.getUloga());
		return korisnikDto;
	}

}
