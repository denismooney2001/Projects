using denisMooney.SOA.CA2.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Services
{
    public interface IAttractionRepository
    {
        //Interface for IAttractionRepository
        IEnumerable<Attraction> GetAttractions(Guid facilityId);
        Attraction GetAttraction(Guid attractionId);
        void AddAttraction(Attraction attraction);
        void UpdateAttraction(Attraction attraction);
        void DeleteAttraction(Attraction attraction);
        IEnumerable<Facility> GetFacilities();

        IEnumerable<Attraction> GetAttractions();
        Facility GetFacility(Guid facilityId);
        void AddFacility(Facility facility);
        void DeleteFacility(Facility facility);
        void UpdateFacility(Facility facility);
        bool FacilityExists(Guid directorId);
        bool Save();
    }
}
