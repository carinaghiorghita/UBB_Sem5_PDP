using System;
using System.Collections.Generic;
using Lab4.Implementation;

namespace Lab4
{
    class Program
    {
        static void Main()
        {
            var hosts = new List<string> {
                //"www.cs.ubbcluj.ro/~hfpop/teaching/pfl/requirements.html",
                //"www.cs.ubbcluj.ro/~forest/newton/index.html",
                //"www.cs.ubbcluj.ro/~rlupsa/edu/pdp/index.html"
                "www.columbia.edu/~fdc/sample.html"
            };

            //DirectCallback.Run(hosts);
            //TasksMechanism.Run(hosts);
            AsyncTasksMechanism.Run(hosts);
        }
    }
}
