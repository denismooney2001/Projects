using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Profiles
{
    public class AttractionsProfile : Profile
    {
        public AttractionsProfile() {
            CreateMap<CA2.Entities.Attraction, Models.AttractionDto>();
            CreateMap<Models.AttractionForCreation, CA2.Entities.Attraction>();
            CreateMap<Models.AttractionForUpdateDto, CA2.Entities.Attraction>();
            CreateMap<CA2.Entities.Attraction, Models.AttractionForUpdateDto>();

        }

    }
}
