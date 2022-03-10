using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Profiles
{
    public class FacilitiesProfile : Profile
    {
        public FacilitiesProfile() {
            CreateMap<CA2.Entities.Facility, Models.FacilityDto>()
                   .ForMember(
                           dest => dest.Name,
                           opt => opt.MapFrom(src => $"{src.Name} "));

            CreateMap<Models.FacilityForCreationDto, CA2.Entities.Facility>();
        }
    }
}
