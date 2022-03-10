using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Models
{
    public class AttractionDto
    {
        //Attraction Data Transfer Object
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public Guid FacilityId { get; set; }

    }
}
