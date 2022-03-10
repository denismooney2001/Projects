using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Entities
{
    public class Attraction
    {
        //Attraction Entity
        [Key]
        public Guid Id { get; set; }

        [Required]
        [MaxLength(100)]
        public string Name { get; set; }

        [Required]
        [MaxLength(1500)]
        public string Description { get; set; }

        [ForeignKey("FacilityId")]
        public Facility Facitility { get; set; }

        public Guid FacilityId { get; set; }
    }
}
