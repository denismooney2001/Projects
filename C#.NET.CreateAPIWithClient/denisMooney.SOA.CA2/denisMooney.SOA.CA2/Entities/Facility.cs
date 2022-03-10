using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace denisMooney.SOA.CA2.Entities
{
    public class Facility
    {
        //Facility Entity
        [Key]
        public Guid Id { get; set; }

        [Required]
        [MaxLength(100)]
        public string Name { get; set; }

        public ICollection<Attraction> Attractions { get; set; }
            = new List<Attraction>();
    }
}
