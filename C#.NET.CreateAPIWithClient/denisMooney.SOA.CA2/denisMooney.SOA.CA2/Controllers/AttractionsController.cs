using AutoMapper;
using denisMooney.SOA.CA2.Entities;
using denisMooney.SOA.CA2.Models;
using denisMooney.SOA.CA2.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Controllers
{
    [ApiController]
    [Route("api/attractions")]
    public class AttractionsController : ControllerBase
    {

        //Attraction Repository
        private readonly IAttractionRepository _attractRepository;

        //Mapper
        private readonly IMapper _mapper;

        //Initialise the controller
        public AttractionsController(IAttractionRepository attractionRepository, IMapper mapper)
        {
            _attractRepository = attractionRepository ??
                throw new ArgumentNullException(nameof(attractionRepository));
            _mapper = mapper ??
               throw new ArgumentNullException(nameof(mapper));
        }

        //method to call all attractions
        public ActionResult<IEnumerable<AttractionDto>> GetAttractions()
        {
            var attractionsFromRepo = _attractRepository.GetAttractions();

            return Ok(_mapper.Map<IEnumerable<AttractionDto>>(attractionsFromRepo));
        }

        [HttpGet("/attractionsByFacility/{facilityId}", Name = "attractionsByFacility")]
        public ActionResult<AttractionDto> GetAttractionsbyFacilitiy(Guid facilityId)
        {
            if (!_attractRepository.FacilityExists(facilityId))
            {
                return NotFound();
            }

            var attractionFromRepo = _attractRepository.GetAttractions(facilityId);

            if (attractionFromRepo == null)
            {
                return NotFound();
            }

            return Ok(_mapper.Map<AttractionDto>(attractionFromRepo));
        }

        //Get Attraction by Id using GET
        [HttpGet("{attractionId}", Name = "GetAttraction")]
        public ActionResult<AttractionDto> GetAttraction(Guid attractionId) {

            var AttractionFromRepo = _attractRepository.GetAttraction(attractionId);

            if (AttractionFromRepo == null)
            {
                return NotFound();
            }

            return Ok(_mapper.Map<AttractionDto>(AttractionFromRepo));
        }

        //Create Attraction using POST
        [HttpPost]
        public ActionResult<AttractionDto> CreateAttraction(AttractionForCreation attraction) {

            
            var attractionEntity = _mapper.Map<CA2.Entities.Attraction>(attraction);
            _attractRepository.AddAttraction(attractionEntity);
            _attractRepository.Save();

            var attractionToReturn = _mapper.Map<AttractionDto>(attractionEntity);

            return CreatedAtRoute("GetAttractionForFacility",
                new { attractionId = attractionToReturn.Id },
                attractionToReturn);
        }

        //Delete Attraction using DELETE
        [HttpDelete("{attractionId}", Name = "deleteAttraction")]
        public ActionResult DeleteAttraction(Guid attractionId)
        {

            var attraction = _attractRepository.GetAttraction(attractionId);

            if (attraction == null)
            {
                return NotFound();
            }

            _attractRepository.DeleteAttraction(attraction);
            _attractRepository.Save();

            return NoContent();
        }

        //Update Attraction using PUT
        [HttpPut]
        public ActionResult UpdateAttraction(Attraction attraction) {

            var existingAttraction = _attractRepository.GetAttraction(attraction.Id);

            _attractRepository.UpdateAttraction(attraction);

            _attractRepository.Save();

            return NoContent();
        }
    }
}
