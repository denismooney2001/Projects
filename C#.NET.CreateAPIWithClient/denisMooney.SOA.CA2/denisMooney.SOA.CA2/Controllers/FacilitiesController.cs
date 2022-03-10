using AutoMapper;
using denisMooney.SOA.CA2.Entities;
using denisMooney.SOA.CA2.Models;
using denisMooney.SOA.CA2.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Controllers
{
    [ApiController]
    [Route("api/facilities")]
    
    public class FacilitiesController : ControllerBase
    {
        //Call Variables
        private readonly IAttractionRepository _attractionRepository;
        private readonly IMapper _mapper;

        public FacilitiesController(IAttractionRepository attractionRepository, IMapper mapper) {
            _attractionRepository = attractionRepository ??
                throw new ArgumentNullException(nameof(attractionRepository));
            _mapper = mapper ??
               throw new ArgumentNullException(nameof(mapper));
        }

        //GET method to retrieve all facilities
        [HttpGet()]
        public ActionResult<IEnumerable<FacilityDto>> GetFacilities()
        {
            var facilitiesFromRepo = _attractionRepository.GetFacilities();
            Console.WriteLine("Facilities = " + facilitiesFromRepo);
            return Ok(_mapper.Map<IEnumerable<FacilityDto>>(facilitiesFromRepo));
        }

        //Get method to get facility by ID 
        [HttpGet("{facilityId}", Name = "GetFacilities")]
        public IActionResult getFacility(Guid facilityId)
        {
            //if (_movieRepository.DirectorExists(directorId))
            //{
            //    return NotFound();
            //}

            var FacilityFromRepo = _attractionRepository.GetFacility(facilityId);

            if (FacilityFromRepo == null)
            {
                return NotFound();
            }

            return Ok(_mapper.Map<FacilityDto>(FacilityFromRepo));
        }

        //POST method to create facility
        [HttpPost]
        public ActionResult<FacilityDto> CreateFactility(FacilityForCreationDto facility)
        {
            
            var facilityEntity = _mapper.Map<Entities.Facility>(facility);
            _attractionRepository.AddFacility(facilityEntity);

            _attractionRepository.Save(); // now saved to database, if db offline this will throw a 500 error

            var facilityToReturn = _mapper.Map<FacilityDto>(facilityEntity);

            return CreatedAtRoute("GetFacility",
                new { facilityId = facilityToReturn.Id },
                facilityToReturn);

        }

    }
}
