using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Models
{
    public class AttractionForCreation
    {
        //Required for creating attraction
        public string Name { get; set; }
        public string Description { get; set; }
        public Guid FacilityId { get; set; }
    }
}
