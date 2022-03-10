using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Models
{
    public class AttractionForUpdateDto
    {
        //Required for updating attraction
        public string Id;
        public string Name;
        public string Description;
        public Guid facilityId;
    }
}
