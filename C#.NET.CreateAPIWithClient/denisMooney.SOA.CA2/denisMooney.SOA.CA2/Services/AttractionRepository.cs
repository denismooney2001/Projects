using denisMooney.SOA.CA2.DbContexts;
using denisMooney.SOA.CA2.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Services
{
    public class AttractionRepository : IAttractionRepository
    {
        //Attraction Repository containing all methods to do with Facility and Attraction tables

        private readonly AttractionDb _context;
        public AttractionRepository(AttractionDb context) {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }
        public void AddAttraction(Attraction attraction)
        {
            //if (FacilityId == Guid.Empty)
            //{
            //    throw new ArgumentNullException(nameof(FacilityId));
            //}

            if (attraction == null)
            {
                throw new ArgumentNullException(nameof(attraction));
            }
            
            _context.Add(attraction);
        }

        public void AddFacility(Facility facility)
        {
            if (facility == null)
            {
                throw new ArgumentNullException(nameof(facility));
            }

            // the repository fills the id (instead of using identity columns)
            facility.Id = Guid.NewGuid();

            foreach (var attraction in facility.Attractions)
            {
                attraction.Id = Guid.NewGuid();
            }

            _context.Add(facility);
        }

        public void DeleteAttraction(Attraction attraction)
        {
            _context.Remove(attraction);
        }

        public void DeleteFacility(Facility facility)
        {
            _context.Facilites.Remove(facility);
        }

        public bool FacilityExists(Guid facilityId)
        {
            if (facilityId == Guid.Empty)
            {
                throw new ArgumentNullException(nameof(facilityId));
            }

            return _context.Facilites.Any(a => a.Id == facilityId);
        }

        public Attraction GetAttraction(Guid attractionId)
        {

            if (attractionId == Guid.Empty)
            {
                throw new ArgumentNullException(nameof(attractionId));
            }

            return _context.Attractions
              .Where(c => c.Id == attractionId).FirstOrDefault();
        }

        public Attraction GetAttraction(Guid facilityId, Guid attractionId)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Attraction> GetAttractions(Guid facilityId)
        {
            if (facilityId == Guid.Empty)
            {
                throw new ArgumentNullException(nameof(facilityId));
            }

            return _context.Attractions
                        .Where(c => c.FacilityId == facilityId)
                        .OrderBy(c => c.Name).ToList();
        }

        public IEnumerable<Attraction> GetAttractions()
        {
            return _context.Attractions;
        }

        public IEnumerable<Facility> GetFacilities()
        {
            return _context.Facilites;
        }

        public Facility GetFacility(Guid facilityId)
        {
            if (facilityId == Guid.Empty)
            {
                throw new ArgumentNullException(nameof(facilityId));
            }

            return _context.Facilites.FirstOrDefault(a => a.Id == facilityId);

        }

        public bool Save()
        {
            return (_context.SaveChanges() >= 0);
        }

        public void UpdateAttraction(Attraction attraction)
        {
            var existingAattraction = _context.Attractions.Where(s => s.Id == attraction.Id).FirstOrDefault<Attraction>();

            if (existingAattraction != null)
            {
                existingAattraction.Name = attraction.Name;
                existingAattraction.Description = attraction.Description;
                existingAattraction.FacilityId = attraction.FacilityId;

                _context.SaveChanges();
            }

        }

        public void UpdateFacility(Facility facility)
        {
            var existingFacility = _context.Facilites.Where(s => s.Id == facility.Id).FirstOrDefault<Facility>();

            if (existingFacility != null)
            {
                existingFacility.Name = facility.Name;

                _context.SaveChanges();
            }
        }

        IEnumerable<Attraction> IAttractionRepository.GetAttractions()
        {
            return _context.Attractions;
        }
    }
}
