class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = -1;
        
        int totalWorks = 0;
        long totalDistance = 0;
        int lastI = 0;
        int lastJ = 0;
        
        for(int i = 0; i < n; i++){
            totalWorks += deliveries[i];
            if(deliveries[i] > 0 ){
                lastI = i;
            }
            totalWorks += pickups[i];
            if(pickups[i] > 0){
                lastJ = i;
            }
        }
        
        // System.out.println("lastI " + );
        
//                 System.out.println("totalWorks " + totalWorks);
//                 System.out.println(" before totalDistance " + totalDistance);

        while(totalWorks > 0){
            // System.out.println("totalWorks " + totalWorks);

            int capPerLoop = cap;
            int nexti = 0; 
            for(int i = lastI; i >= 0 ; i--){
                if(cap > 0 && deliveries[i] > 0){
                    if(capPerLoop >= deliveries[i]){
                        capPerLoop -= deliveries[i];
                        totalWorks -= deliveries[i];
                        deliveries[i] = 0;
                    } else {
                        deliveries[i] -= capPerLoop;
                        totalWorks -= capPerLoop;
                        capPerLoop = 0;
                    }
                    
                    
                    
                }
                
                if(capPerLoop == 0 && deliveries[i] > 0){
                    nexti = i;
                    break;
                }
                
                
                
                
            }
            
            capPerLoop = 0;
            int nextj = 0;
            for(int j = lastJ; j >= 0; j--){
                if(cap > capPerLoop && pickups[j] > 0){
                    if(capPerLoop + pickups[j] <= cap){
                        capPerLoop += pickups[j];
                        totalWorks -= pickups[j];
                        pickups[j] = 0;
                    } else {
                        pickups[j] -= (cap - capPerLoop);
                        totalWorks -= (cap - capPerLoop);
                        capPerLoop = cap;
                    }
                    
                    
                    
                    
                }
                
                if(capPerLoop == cap && pickups[j] > 0){
                    nextj = j;
                    break;
                }
                
                
                
                
                
            }
            
            totalDistance += (Math.max(lastI, lastJ) + 1) * 2;
            
            
            lastI = nexti;
            lastJ = nextj;
            
            
            
            
            
            
        }
        
        // System.out.println("after totalDistance " + totalDistance);

        
        return totalDistance;
    }
}